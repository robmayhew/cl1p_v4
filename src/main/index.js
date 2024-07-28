const express = require('express');
const app = express();
const apiRoutes = require('./routes/api');
const { swaggerUi, specs } = require('./swagger');
const PORT = process.env.PORT || 3000;

app.use('/api', apiRoutes);
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(specs));

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

