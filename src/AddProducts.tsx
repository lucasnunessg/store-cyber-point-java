import { useState } from 'react';
import api from "./FetchApi";
import './AddProductsStyle.css'; // Certifique-se de importar o arquivo de estilo

function AddProducts() {
  const [productName, setProductName] = useState('');
  const [productPrice, setProductPrice] = useState('');
  const [productDescription, setProductDescription] = useState('');
  const [productCategory, setProductCategory] = useState('');
  const [productImage, setProductImage] = useState('');
  const token = localStorage.getItem('token');

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    window.location.reload();
    try {
      const product = {
        name: productName,
        price: productPrice,
        description: productDescription,
        category: productCategory,
        image: productImage,
      };
      const response = await api.post("/products", product, {
        headers: {
          Authorization: `Bearer ${token}`,
        }
      });
      console.log(response.data);

      // Reset form fields
      setProductName('');
      setProductPrice('');
      setProductDescription('');
      setProductImage('');
      setProductCategory('');
    } catch (error) {
      console.error("Erro ao adicionar o produto:", error);
    }
  };

  return (
    <div className="new-product-container">
      <form onSubmit={handleSubmit} className="product-form">
        <div className="form-field">
          <h1 className="form-heading">Adicionar Novo Produto</h1>
          <label className="label-input">Nome do Produto:</label>
          <input
            type="text"
            value={productName}
            onChange={(e) => setProductName(e.target.value)}
            className="input-field"
            placeholder="Nome do produto"
          />
        </div>
        <div className="form-field">
          <label className="label-input">Preço:</label>
          <input
            type="text"
            value={productPrice}
            onChange={(e) => setProductPrice(e.target.value)}
            className="input-field"
            placeholder="Preço do produto"
          />
        </div>
        <div className="form-field">
          <label className="label-input">Descrição:</label>
          <textarea
            value={productDescription}
            onChange={(e) => setProductDescription(e.target.value)}
            className="input-field"
            placeholder="Descrição do produto"
          />
        </div>
        <div className="form-field">
          <label className="label-input">Imagem:</label>
          <input
            type="text"
            value={productImage}
            onChange={(e) => setProductImage(e.target.value)}
            className="input-field"
            placeholder="URL da imagem"
          />
        </div>
        <div className="form-field">
          <label className="label-input">Categoria:</label>
          <input
            type="text"
            value={productCategory}
            onChange={(e) => setProductCategory(e.target.value)}
            className="input-field"
            placeholder="Categoria do produto"
          />
        </div>
        <div className="submit-button">
          <button type="submit" className="submit-btn">Adicionar Produto</button>
        </div>
      </form>
    </div>
  );
}

export default AddProducts;