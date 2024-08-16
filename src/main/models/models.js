const mongoose = require('mongoose');

// Define a schema and model
const RMPathKeySchema = new mongoose.Schema({
    uri: String,
    id: String,
});

const RMPathKey = mongoose.model('RMPathKey', RMPathKeySchema);

const RMPathTextContentSchema = new mongoose.Schema({
    text: {type:String, required: true},
    pathKey: {type: mongoose.Schema.Types.ObjectId, ref: 'RMPathKey'}
});

const RMPathTextContent =  mongoose.model('RMPathTextContent', RMPathTextContentSchema);

module.exports = {
    RMPathKey,
    RMPathTextContent
};