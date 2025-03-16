import express from 'express';
import dotenv from 'dotenv';
dotenv.config();
import './db';
import path from 'path';
import wildcardRouter from './routes/wildcard'; 
const bodyParser = require('body-parser');


const app = express();
const PORT = process.env.PORT || 3000;


app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.json());
app.set('view engine', 'ejs');
app.set("views", path.join(__dirname, "views"));
app.use(express.urlencoded({ extended: true }));


// Setup EJS for server-side rendering
app.get('/test', (req, res) => {
  res.render('index', { title: 'Home Page', message: 'Hello there!' });
});

app.use('/', wildcardRouter);

app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
