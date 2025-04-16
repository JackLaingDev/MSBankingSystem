import React, { useState } from 'react';
import { TextField, Button, Box, Typography } from '@mui/material';
import axios from 'axios';

function Register() {
  const [form, setForm] = useState({
    username: '',
    password: '',
    firstName: '',
    lastName: ''
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/api/customers/register', form);
      alert(res.data);
    } catch (err) {
      alert('Error: ' + err.response?.data?.message || err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
        <Box display="flex" flexDirection="column" gap={2} maxWidth={300} mx="auto" mt={5}>
          <Typography variant="h5" align="center">Register</Typography>
          <TextField name="username" label="Username" onChange={handleChange} />
          <TextField name="password" label="Password" type="password" onChange={handleChange} />
          <TextField name="firstName" label="First Name" onchange={handleChange} />
          <TextField name="lastName" label="Last Name" onchange={handleChange} />
          <Button type="submit" variant="contained">Register</Button>
        </Box>
    </form>
  );
}

export default Register;
