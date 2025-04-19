
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Container,
  Typography,
  Box,
  Divider,
  Paper,
  Button
} from '@mui/material';

function Account_Individual() {
  const { accountID } = useParams();
  const [account, setAccount] = useState(null);
  const [transactions, setTransactions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`https://jlbanking.ew.r.appspot.com/api/accounts/${accountID}`)
      .then(res => setAccount(res.data))
      .catch(err => console.error(err));

    axios.get(`https://jlbanking.ew.r.appspot.com/api/transactions/history/${accountID}`)
      .then(res => setTransactions(res.data))
      .catch(err => console.error(err));
  }, [accountID]);

  const handleCloseAccount = async () => {
    try {
      await axios.delete(`https://jlbanking.ew.r.appspot.com/api/accounts/${accountID}`);
      alert('Account closed successfully');
      navigate('/accounts');
    } catch (err) {
      alert('Failed to close account: ' + err.response?.data?.message || err.message);
    }
  };

  if (!account) return <Typography>Loading account...</Typography>;

  return (
    <Container sx={{ mt: 4 }}>
      <Paper sx={{ p: 3, mb: 4 }}>
        <Typography variant="h4" gutterBottom>Account Details</Typography>
        <Box mt={2}>
          <Typography><strong>Account ID:</strong> {account.accountID}</Typography>
          <Typography><strong>Balance:</strong> £{account.balance}</Typography>
          <Typography><strong>Type:</strong> {account.accountType}</Typography>
        </Box>
        <Button variant="contained" color="error" onClick={handleCloseAccount} sx={{ mt: 2 }}>
          Close Account
        </Button>
      </Paper>

      <Paper sx={{ p: 3 }}>
        <Typography variant="h6" gutterBottom>Transaction History</Typography>
        <Divider sx={{ mb: 2 }} />
        {transactions.length === 0 ? (
          <Typography>No transactions found.</Typography>
        ) : (
          transactions.map((tx, index) => (
            <Box key={index} mb={2} p={1} borderBottom="1px solid #eee">
              <Typography variant="body2">
                {tx.sender.accountID === account.accountID ? "Sent" : "Received"} £{tx.amount}
                {` to/from Account ${tx.sender.accountID === account.accountID ? tx.recipient.accountID : tx.sender.accountID}`}
              </Typography>
            </Box>
          ))
        )}
      </Paper>
    </Container>
  );
}

export default Account_Individual;
