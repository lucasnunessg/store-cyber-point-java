import React, { useState } from 'react';
import api from './FetchApi';
import Product from './interface/Product';

interface EditProductProps {
  product: Product;
  onSave: (product: Product) => void;
  onCancel: () => void;
}

const EditProduct: React.FC<EditProductProps> = ({ product, onSave, onCancel }) => {
  const [name, setName] = useState<string>(product.name);
  const [price, setPrice] = useState<number>(product.price);
  const [description, setDescription] = useState<string>(product.description);
  const [image, setImage] = useState<string>(product.image);
  const token = localStorage.getItem('token');

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const updatedProduct = { ...product, name, price, description, image };
      await api.put(`/products/${product.id}`, updatedProduct, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      onSave(updatedProduct);
      window.location.reload();
    } catch (error) {
      console.error('Erro ao editar produto', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Nome do produto</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />

        <label>Preço</label>
        <input
          type="number"
          value={price}
          onChange={(e) => setPrice(Number(e.target.value))}
        />

        <label>Descrição</label>
        <input
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <label>Imagem</label>
        <input
          type="text"
          value={image}
          onChange={(e) => setImage(e.target.value)}
        />
      </div>
      <button type="submit">Salvar alterações</button>
      <button type="button" onClick={onCancel}>Cancelar</button>
    </form>
  );
};

export default EditProduct;
