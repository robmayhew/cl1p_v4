"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.WildcardModel = void 0;
const mongoose_1 = require("mongoose");
const wildcardSchema = new mongoose_1.Schema({
    uri: { type: String, required: true },
    content: { type: String, required: true },
}, {
    timestamps: { createdAt: 'created', updatedAt: 'lastAccess' },
});
const WildcardModel = (0, mongoose_1.model)("Wildcard", wildcardSchema);
exports.WildcardModel = WildcardModel;
