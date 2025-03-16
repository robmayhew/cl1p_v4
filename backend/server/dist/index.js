"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const dotenv_1 = __importDefault(require("dotenv"));
dotenv_1.default.config();
require("./db");
const path_1 = __importDefault(require("path"));
const wildcard_1 = __importDefault(require("./routes/wildcard"));
const bodyParser = require('body-parser');
const app = (0, express_1.default)();
const PORT = process.env.PORT || 3000;
app.use(express_1.default.static(path_1.default.join(__dirname, 'public')));
app.use(bodyParser.json());
app.set('view engine', 'ejs');
// Setup EJS for server-side rendering
app.get('/test', (req, res) => {
    res.render('index', { title: 'Home Page', message: 'Hello there!' });
});
app.use('/', wildcard_1.default);
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
