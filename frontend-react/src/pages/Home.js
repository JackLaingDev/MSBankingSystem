import React from 'react';
import Navbar from './Navbar';
import { Container, Typography } from '@mui/material';

function Home() {
  return (
    <>
      <Navbar />
      <Container>
        <Typography variant="h4" gutterBottom>
          Welcome to MSBank!
        </Typography>
        <Typography>
          This is your banking dashboard.
        </Typography>
      </Container>
    </>
  );
}

export default Home;
