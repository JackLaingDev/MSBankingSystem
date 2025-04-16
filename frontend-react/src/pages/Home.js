import React from 'react';
import Navbar from '../components/Navbar';
import { Container, Typography } from '@mui/material';

function Home() {
  return (
    <>
      <Navbar />
      <Container>
        <Typography variant="h4" gutterBottom>
          Welcome to Jack Laings Banking App!
        </Typography>
        <Typography>
          This is your banking dashboard.
        </Typography>
      </Container>
    </>
  );
}

export default Home;
