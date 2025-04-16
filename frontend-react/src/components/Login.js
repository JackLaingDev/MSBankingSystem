import React, { useState } from 'react';
import { TextField, Button, Box, Typography } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Login({setIsLoggedIn, setCustomerID }) {
  const [form, setForm] = useState({ username: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/api/customers/login', form);
      console.log("Login response:", res.data);
      if (res.data === "Login successful") {

        const customerRes = await axios.get(`http://localhost:8080/api/customers/username/${form.username}`);
        setCustomerID(customerRes.data.customerID);
        setIsLoggedIn(true);
        alert("Logged in!");
        navigate('/accounts');
      } else {
        alert(res.data);
      }
    } catch (err) {
      alert('Login failed: ' + err.response?.data?.message || err.message);
    }
  };

  return (
      <>
      <form onSubmit={handleSubmit}>
        <Box display="flex" flexDirection="column" gap={2} maxWidth={300} mx="auto" mt={5}>
          <Typography variant="h5" align="center">Login</Typography>
          <TextField name="username" label="Username" onChange={handleChange} />
          <TextField name="password" label="Password" type="password" onChange={handleChange} />
          <Button type="submit" variant="contained">Login</Button>
        </Box>
      </form>
      <Box display="flex" flexDirection="column" gap={2} maxWidth={300} mx="auto" mt={5}>
        <Typography variant="h5" align="center">Don't have an account? Register below!</Typography>
        <Button color="inherit" component={Link} to="/register">Register</Button>
      </Box>
      </>
  );
}

export default Login;
