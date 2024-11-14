import { ChangeEvent, useCallback, useEffect, useState } from 'react';
import Product from './interface/Product';
import api from './FetchApi';

interface ApiProps {
  onNextPageClick?: () => void;
}

function Products({ onNextPageClick }: ApiProps) {
  const [products, setProducts] = useState<Product[]>([]);
  const [startExibition, setStartExibition] = useState<number>(0);
  const [loading, setLoading] = useState<boolean>(true);
  const [search, setSearch] = useState<string>("");
  const productPerPage = 5;

  useEffect(() => {
    async function FetchProducts() {
      setLoading(true);
      try {
        const response = await api.get('/products');
        if (Array.isArray(response.data)) {
          setProducts(response.data);
          console.log("Produtos carregados:", response.data);
        } else {
          console.log("A resposta não é um array:", response.data);
        }
      } catch (e) {
        console.log("Não foi possível fazer o fetch", e);
      } finally {
        setLoading(false);
      }
    }
    FetchProducts();
  }, []);

  const handleNextPage = useCallback(() => {
    setStartExibition(prev => prev + productPerPage);
    if (onNextPageClick) {
      onNextPageClick();
    }
  }, [productPerPage, onNextPageClick]);

  const returnPage = useCallback(() => {
    setStartExibition(prev => prev - productPerPage);
  }, [productPerPage]);

  const searchProducts = (event: ChangeEvent<HTMLInputElement>) => {
    setSearch(event.target.value);
    setStartExibition(0); // Reset para a primeira página ao buscar
  };

  // Filtrar os produtos com base no termo de busca
  const filteredProducts = products.filter(product =>
    product.name?.toLowerCase().includes(search.toLowerCase())
  );

  console.log("Produtos filtrados:", filteredProducts); // Verificar o conteúdo de filteredProducts

  const isPreviousDisabled = startExibition === 0;
  const isNextDisabled = startExibition + productPerPage >= filteredProducts.length;

  return (
    <div>
      <h1>Produtos</h1>
      <div>
        <input
          type='text'
          placeholder='Digite o nome do produto'
          value={search}
          onChange={searchProducts}
        />
        <button onClick={returnPage} disabled={isPreviousDisabled}>Página anterior</button>
        <button onClick={handleNextPage} disabled={isNextDisabled}>Próxima</button>
      </div>

      {loading ? (
        <p>Carregando produtos...</p>
      ) : filteredProducts.length === 0 ? (
        <p>Nenhum produto encontrado.</p>
      ) : (
        <ul>
          {filteredProducts.slice(startExibition, startExibition + productPerPage).map((product) => (
            <div key={product.id}>
              <h2>{product.name}</h2>
              <img src={product.image} alt={product.name}/>
              <p className='description'>{product.description}</p>
              <p>R$ {product.price}</p>
            </div>
          ))}
        </ul>
      )}

      <div>
        <button onClick={returnPage} disabled={isPreviousDisabled}>Página anterior</button>
        <button onClick={handleNextPage} disabled={isNextDisabled}>Próxima</button>
      </div>
    </div>
  );
}

export default Products;
