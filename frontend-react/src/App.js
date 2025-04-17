import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Accounts from './pages/Account';
import Transfer from './pages/Transfer';

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
        <Route path="/transfer" element={<Transfer customerID={customerID} />} />
      </Routes>
    </Router>
  );
}

export default App;
