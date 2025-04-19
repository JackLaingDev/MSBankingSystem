import { Link } from 'react-router-dom';
import {
  Container,
  Typography,
  Button,
  Grid,
  Box,
  Paper,
  List,
  ListItem,
  ListItemText
} from '@mui/material';

function Home() {
  return (
    <Container sx={{ mt: 4 }}>
      <Grid container spacing={4}>

        {/* Row 1: Full width - Project Purpose */}
        <Grid item xs={12}>
          <Paper sx={{ p: 3 }}>
            <Typography variant="h5" gutterBottom>
              Purpose of this project
            </Typography>
            <Typography>
              This project began as a CLI-based monolithic banking system I built to demonstrate core concepts like user authentication, account management, and secure transactions. Inspired by Atom Bank’s modern tech stack and architecture, I restructured it into a microservice-friendly, full-stack web application. By combining a Spring Boot backend with a React frontend, the system now follows a scalable and modular architecture that reflects real-world banking systems.
            </Typography>
            <Typography variant="h5" gutterBottom sx={{mt:5}}>
                  Features
                </Typography>
                <List dense>
                  <ListItem><ListItemText primary="• Register as a new customer" /></ListItem>
                  <ListItem><ListItemText primary="• Login securely to your account" /></ListItem>
                  <ListItem><ListItemText primary="• Open multiple bank accounts" /></ListItem>
                  <ListItem><ListItemText primary="• Transfer money between accounts" /></ListItem>
                  <ListItem><ListItemText primary="• View account balances in real time" /></ListItem>
                </List>
          </Paper>
          
        </Grid>

        {/* Row 2: Side-by-side Grid items */}
        <Grid item xs={12}>
          <Grid container spacing={4}>
            {/* Get Started */}
            <Grid item xs={12} md={6}>
              <Paper sx={{ p: 3 }}>
                <Typography variant="h5" gutterBottom>
                  Get Started with JLBank
                </Typography>
                <Typography>
                  Creating an account is quick and easy. Register a new customer, log in, and begin managing your accounts.
                </Typography>
                <Box mt={2}>
                  <Button variant="contained" component={Link} to="/register" sx={{ mr: 1 }}>Register</Button>
                  <Button variant="outlined" component={Link} to="/login">Login</Button>
                </Box>
              </Paper>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </Container>
  );
}

export default Home;
