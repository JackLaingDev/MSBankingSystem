import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Typography, Button, Box } from '@mui/material';

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
      customer: { customerID: customerID },
      accountType: 0,
      balance: 0
    };
    await axios.post('http://localhost:8080/api/accounts/create', newAcc);
    // Refresh accounts list
    const res = await axios.get(`http://localhost:8080/api/customers/${customerID}/accounts`);
    setAccounts(res.data);
  };

  return (
    <>
      <Container>
        <Typography variant="h5" gutterBottom>My Accounts</Typography>
        {accounts.map((acc) => (
          <Box key={acc.accountID} mb={2}>
            <Typography>Account ID: {acc.accountID}</Typography>
            <Typography>Balance: £{acc.balance}</Typography>
            <Typography>Type: {acc.accountType}</Typography>
          </Box>
        ))}
        <Button variant="contained" onClick={openAccount}>Open New Account</Button>
      </Container>
      <Button color="inherit" component={Link} to="/transfer">Transfer</Button>
    </>
  );
}

export default Accounts;
