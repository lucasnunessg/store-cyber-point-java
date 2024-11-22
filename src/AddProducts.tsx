import { useState } from 'react';
import api from "./FetchApi";

function AddProducts() {
  const [name, setName] = useState('');
  const [price, setPrice] = useState('');
  const [description, setDescription] = useState('');
  const [category, setCategory] = useState('');
  const [image, setImage] = useState('');
  const token = localStorage.getItem('token');

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    window.location.reload();
    try {
      const product = {
        name,
        price,
        description,
        category,
        image,
      };
      const response = await api.post("/products", product, {
        headers: {
          Authorization: `Bearer ${token}`,
        }
      });
      console.log(response.data);

      // Reset form fields
      setName('');
      setPrice('');
      setDescription('');
      setImage('');
      setCategory('');
    } catch (error) {
      console.error("Erro ao adicionar o produto:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <h1>Adicionar Produto:</h1>
        <label>Nome:</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>
      <div>
        <label>Preço:</label>
        <input
          type="text"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
        />
      </div>
      <div>
        <label>Descrição:</label>
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </div>
      <div>
        <label>Imagem:</label>
        <input
          type="text"
          value={image}
          onChange={(e) => setImage(e.target.value)}
        />
      </div>
      <div>
        <label>Categoria:</label>
        <input
          type="text"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        />
      </div>
      <button type="submit">Adicionar Produto</button>
    </form>
  );
}

export default AddProducts;
