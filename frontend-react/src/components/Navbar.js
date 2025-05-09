import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

function Navbar({ isLoggedIn }) {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          JLBank
        </Typography>
        <Button color="inherit" component={Link} to="/">Home</Button>
        {isLoggedIn && ( // Only show if logged in
          <Button color="inherit" component={Link} to="/accounts">Accounts</Button>
        )}
        {!isLoggedIn &&(
          <Button color="inherit" component={Link} to="/login">Login</Button>
        )}
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;
