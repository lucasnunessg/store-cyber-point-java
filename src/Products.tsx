import { ChangeEvent, useCallback, useEffect, useState } from 'react';
import Product from './interface/Product';
import api from './FetchApi';
import AddProducts from './AddProducts';
import { jwtDecode } from 'jwt-decode';
import DeleteProduct from './DeleteProduct';
import EditProduct from './EditProduct';

interface ApiProps {
  onNextPageClick?: () => void;
}

function Products({ onNextPageClick }: ApiProps) {
  const [products, setProducts] = useState<Product[]>([]);
  const [startExibition, setStartExibition] = useState<number>(0);
  const [loading, setLoading] = useState<boolean>(true);
  const [deleteProductId, setDeleteProductId] = useState<number | null>(null); // Estado para controlar qual produto será excluído.
  const [search, setSearch] = useState<string>("");
  const [minPrice, setMinPrice] = useState<number>(0);
  const [maxPrice, setmaxPrice] = useState<number>(10000);
  const [editingProduct, setEditingProduct] = useState<Product | null>(null);
  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
  const productPerPage = 5;

  interface DecodedToken {
    role: string;
  }

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken: DecodedToken = jwtDecode(token);
        setIsAuthenticated(decodedToken.role === "ROLE_ADMIN");
      } catch (error) {
        console.error("Erro ao decodificar", error);
        setIsAuthenticated(false);
      }
    }
  }, []);

  useEffect(() => {
    async function FetchProducts() {
      setLoading(true);
      try {
        const response = await api.get('/products');
        if (Array.isArray(response.data)) {
          setProducts(response.data);
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

  const handleCategoryChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { value, checked } = event.target;
    setSelectedCategories(prev =>
      checked ? [...prev, value] : prev.filter(category => category !== value)
    );
  };

  const handleDeleteProduct = async (productId: number) => {
    try {
      await api.delete(`/products/${productId}`);
      setProducts(products.filter(product => product.id !== productId));
      console.log(products);
      window.location.reload();
    } catch (error) {
      console.error('Error deleting product:', error);
    }
  };

  const handleEditProduct = (product: Product) => {
    console.log("Produto sendo editado:", product);
    setEditingProduct(product);
  };

  const handleSave = (updatedProduct: Product) => {
    setProducts(products.map((product) =>
      product.id === updatedProduct.id ? updatedProduct : product
    ));
    setEditingProduct(null);
  };

  const handleCancel = () => {
    setEditingProduct(null);
  };

  const handleCancelDelete = () => {
    setDeleteProductId(null);
  };

  // Filtrar os produtos com base no termo de busca
  const filteredProducts = products
    .filter(product =>
      product.name.toLowerCase().includes(search.toLowerCase()) &&
      product.price >= minPrice && product.price <= maxPrice &&
      (selectedCategories.length === 0 || selectedCategories.includes(product.category.toLowerCase()))
    );

  const isPreviousDisabled = startExibition === 0;
  const isNextDisabled = startExibition + productPerPage >= filteredProducts.length;

  if (isAuthenticated === null) {
    return <p>Verificando autenticação...</p>;
  }

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

      <div>
        <label>Preço Mínimo</label>
        <input
          type="number"
          value={minPrice}
          onChange={(e) => setMinPrice(Number(e.target.value))}
        />
        <label>Preço Máximo</label>
        <input
          type="number"
          value={maxPrice}
          onChange={(e) => setmaxPrice(Number(e.target.value))}
        />
      </div>

      <div>
        <h3>Categorias</h3>
        <label>
          <input
            type="checkbox"
            value="electronic"
            checked={selectedCategories.includes('electronic')}
            onChange={handleCategoryChange}
          />
          Eletrônicos
        </label>
        <label>
          <input
            type="checkbox"
            value="peripheral"
            checked={selectedCategories.includes('peripheral')}
            onChange={handleCategoryChange}
          />
          Periféricos
        </label>
      </div>

      {isAuthenticated && (
        <AddProducts />
      )}

      {loading ? (
        <p>Carregando produtos...</p>
      ) : filteredProducts.length === 0 ? (
        <p>Nenhum produto encontrado.</p>
      ) : (
        <ul>
          {filteredProducts.slice(startExibition, startExibition + productPerPage).map((product) => (
            <div key={product.id}>
              <h2>{product.name}</h2>
              <img src={product.image} alt={product.name} />
              <p className='description'>{product.description}</p>
              <p>R$ {product.price}</p>
              {isAuthenticated && (
                <>
                  <button onClick={() => handleEditProduct(product)}>Editar</button>
                  <DeleteProduct product={product} onDelete={() => handleDeleteProduct(product.id)} onCancel={() => {}} />
                </>
              )}

              {/* Exibe o EditProduct abaixo do produto, caso o produto esteja sendo editado */}
              {editingProduct && editingProduct.id === product.id && (
                <div>
                  <EditProduct
                    product={editingProduct}
                    onSave={handleSave}
                    onCancel={handleCancel}
                  />
                </div>
              )}
            </div>
          ))}
        </ul>
      )}

      {deleteProductId !== null && (
        <DeleteProduct
          product={products.find((product) => product.id === deleteProductId)!}
          onDelete={handleDeleteProduct}
          onCancel={handleCancelDelete}
        />
      )}

      <div>
        <button onClick={returnPage} disabled={isPreviousDisabled}>Página anterior</button>
        <button onClick={handleNextPage} disabled={isNextDisabled}>Próxima</button>
      </div>
    </div>
  );
}

export default Products;
