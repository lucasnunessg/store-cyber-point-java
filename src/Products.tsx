import { useEffect, useState } from 'react';
import Product from './interface/Product';
import api from './FetchApi';

function Products() {
  const [products, setProducts] = useState<Product[]>([]);

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

  return (
    <div>
      <h1>Produtos</h1>
      <ul>
        {products.map((product) => (
          <div key={product.id}>
          <h2>{product.title}</h2>
          <img src={product.image} alt={product.title}/>
          <p className='description'>{product.description}</p>
          <p>R$ {product.price}</p>
          
          </div>
        ))}
      </ul>
    </div>
  );

}

export default Products;