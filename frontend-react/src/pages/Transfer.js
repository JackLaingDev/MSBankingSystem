import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Container,
  Typography,
  TextField,
  MenuItem,
  Button,
  Box
} from '@mui/material';


function Transfer({ customerID }) {
  const [accounts, setAccounts] = useState([]);
  const [senderID, setSenderID] = useState('');
  const [recipientID, setRecipientID] = useState('');
  const [amount, setAmount] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const transaction = {
      sender: { accountID: parseInt(senderID) },
      recipient: { accountID: parseInt(recipientID) },
      amount: parseFloat(amount)
    };

    try {
      await axios.post('http://localhost:8080/api/transactions/send', transaction);
      alert('Transfer successful!');
      navigate('/accounts');
    } catch (err) {
      alert('Transfer failed: ' + err.response?.data?.message || err.message);
    }
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h5" gutterBottom>Transfer Funds</Typography>
      <form onSubmit={handleSubmit}>
        <Box display="flex" flexDirection="column" gap={2}>
          <TextField
            select
            label="Sender Account"
            value={senderID}
            onChange={(e) => setSenderID(e.target.value)}
            required
          >
            {accounts.map((acc) => (
              <MenuItem key={acc.accountID} value={acc.accountID}>
                {`ID: ${acc.accountID} | Balance: £${acc.balance}`}
              </MenuItem>
            ))}
          </TextField>

          <TextField
            label="Recipient Account ID"
            type="number"
            value={recipientID}
            onChange={(e) => setRecipientID(e.target.value)}
            required
          />

          <TextField
            label="Amount"
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />

          <Button type="submit" variant="contained">Send</Button>
        </Box>
      </form>
    </Container>
  );
}

export default Transfer;
