import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Typography, Button, Box, Paper, Grid } from '@mui/material';
import { Link } from 'react-router-dom';

function Accounts({ isLoggedIn, customerID }) {
  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    if (isLoggedIn && customerID) {
      axios.get(`http://localhost:8080/api/customers/${customerID}/accounts`)
        .then(res => setAccounts(res.data))
        .catch(err => console.error(err));
    }
  }, [isLoggedIn, customerID]);

  const openAccount = async () => {
    const newAcc = {
      customer: { customerID },
      accountType: 0,
      balance: 0
    };
    await axios.post('http://localhost:8080/api/accounts/create', newAcc);
    const res = await axios.get(`http://localhost:8080/api/customers/${customerID}/accounts`);
    setAccounts(res.data);
  };

  return (
    <Container sx={{ mt: 4 }}>
      <Typography variant="h5" gutterBottom>My Accounts</Typography>

      <Grid container spacing={3}>
        {accounts.map((acc) => (
          <Grid item xs={12} sm={6} md={4} key={acc.accountID}>
            <Paper sx={{ p: 3 }}>
              <Typography>Account ID: {acc.accountID}</Typography>
              <Typography>Balance: £{acc.balance}</Typography>
              <Typography>Type: {acc.accountType}</Typography>
              <Button
                variant="outlined"
                component={Link}
                to={`/accounts/${acc.accountID}`}
                sx={{ mt: 1 }}
              >
                View
              </Button>
            </Paper>
          </Grid>
        ))}
      </Grid>

      <Box mt={4} display="flex" gap={2}>
        <Button variant="contained" onClick={openAccount}>Open New Account</Button>
        <Button variant="contained" color="inherit" component={Link} to="/transfer">Transfer</Button>
      </Box>
    </Container>
  );
}

export default Accounts;
