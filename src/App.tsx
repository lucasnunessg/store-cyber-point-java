import { Routes, Route, BrowserRouter } from 'react-router-dom';

import './App.css';
import Products from './Products.tsx';
import Header from './Header.tsx';
import Persons from './Persons.tsx';
import MainRoute from './MainRoute.tsx';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<MainRoute />} />
        <Route path="/products" element={<Products />} />
        <Route path="/persons" element={<Persons />} />
      </Routes>
      </BrowserRouter>

  );
}

export default App;
