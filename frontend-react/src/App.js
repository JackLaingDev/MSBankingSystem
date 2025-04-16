import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import Home from './pages/Home';
import Navbar from './components/Navbar';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [customerID, setCustomerID] = useState(null);

  return (
    <Router>
      <Navbar isLoggedIn={isLoggedIn} />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={
              <Login setIsLoggedIn={setIsLoggedIn} setCustomerID={setCustomerID} />
}              />
        <Route path="/accounts" element={
              <Accounts isLoggedIn={isLoggedIn} customerID={customerID} />
            } />
      </Routes>
    </Router>
  );
}

export default App;
