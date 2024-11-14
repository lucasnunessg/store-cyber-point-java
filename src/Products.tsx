import { useCallback, useEffect, useState } from 'react';
import Product from './interface/Product';
import api from './FetchApi';

interface ApiProps {
  onNextPageClick?: () => void;
}


function Products({ onNextPageClick }: ApiProps) {
  const [products, setProducts] = useState<Product[]>([]);
  const [startExibition, setStartExibition] = useState<number>(0);
  const productPerPage = 5;

  useEffect(() => {
    async function FetchProducts() {
    try{
      const response = await api.get('/products')
      if(Array.isArray(response.data)) {
        setProducts(response.data);
      }
    }catch(e) {
      console.log("Não foi possível fazer o fetch", e);
    }
    }
    FetchProducts();
  }, []);

  const handleNextPage = useCallback(() => {
    setStartExibition(prev => prev + productPerPage);
    if(onNextPageClick) {
      onNextPageClick();
    }
  }, [productPerPage, onNextPageClick]);

  const returnPage = useCallback(() =>{
    setStartExibition(prev => prev - productPerPage);
  }, [productPerPage]);

  const isPreviousDisabled = startExibition === 0;
  const isNextDisabled = startExibition + productPerPage >= products.length;

  return (
    <div>
      <h1>Produtos</h1>
      <div>

      <button onClick={returnPage} disabled={isPreviousDisabled}>Página anterior</button>


      <button onClick={handleNextPage} disabled={isNextDisabled}>Próxima</button>

</div>
      <ul>
        {products.slice(startExibition, startExibition + productPerPage).map((product) => (
          <div key={product.id}>
          <h2>{product.title}</h2>
          <img src={product.image} alt={product.title}/>
          <p className='description'>{product.description}</p>
          <p>R$ {product.price}</p>
          
          </div>
        ))}
      </ul>
      <div>

<button onClick={returnPage} disabled={isPreviousDisabled}>Página anterior</button>





<button onClick={handleNextPage} disabled={isNextDisabled}>Próxima</button>

</div>

    </div>
  );

}

export default Products;