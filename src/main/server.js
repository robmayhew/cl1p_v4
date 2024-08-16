const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const apiRoutes = require('./routes/api');
const { swaggerUi, specs } = require('./swagger');
const mongoose = require('mongoose');
const path = require('path');

const PORT = process.env.PORT || 3000;

// Middleware
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, 'public')));

// Set EJS as the templating engine
app.set('view engine', 'ejs');

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/mydatabase', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', () => {
  console.log('Connected to MongoDB');
});

// Define a schema and model
const cl1pSchema = new mongoose.Schema({
  uri: String,
  content: String,
});

const Cl1p = mongoose.model('Cl1p', cl1pSchema);



app.use('/api', apiRoutes);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(specs));

// Route to serve the index.html file
app.get('/', (req, res) => {
  const data = { message: 'Welcome to the server-side rendered index page!' };
  res.render('index', data);
  res.headersSent = true;
});

// Example route using Mongoose
app.get('*', async (req, res) => {
  if(res.headersSent)
  {
    return next();
  }
  try {
    const cl1p = await Cl1p.findOne({ uri: req.url });
    if (!cl1p) {
      const data = {cl1pPath: req.url, message: 'See'}
      res.render('cl1p_view',data );
      return;
    }
    res.json(cl1p);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

app.post('*', async (req, res) => {
  try {
    const cl1p = new Cl1p(req.body);
    await cl1p.save();
    res.status(201).json(cl1p);
  } catch (err) {
    res.status(500).send(err.message);
  }
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});