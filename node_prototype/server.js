const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const path = require('path');

const app = express();
const port = 3000;

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

// Define a schema
const cl1pSchema = new mongoose.Schema({
  uri: String,
  content: String,
});

const Cl1p = mongoose.model('Cl1p', cl1pSchema);





// Wildcard route to handle all other routes
app.get('*', async (req, res) => {
  let cl1p = await Cl1p.findOne({ uri: req.path });
  if(!cl1p){
    cl1p = new Cl1p({ uri: req.path, content: '' });
  }
  res.render('cl1p', { cl1p: cl1p, currentPath : req.path }); 
});

app.post('*', async (req, res) => {
  console.log('POST request received to ' + req.path)
  const cl1p = new Cl1p( req.body);
  await cl1p.save();
  res.render('cl1p', { cl1p: cl1p, currentPath : req.path }); 
})  

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
