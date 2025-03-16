"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const wildard_1 = require("../models/wildard");
const router = express_1.default.Router();
router.get('*', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    console.log(`GET request received for path: ${req.path}`);
    let wild = yield wildard_1.WildcardModel.findOne({ uri: req.path });
    const empty = !wild;
    if (empty) {
        console.log(`No wildcard found for path: ${req.path}, creating new one.`);
        wild = new wildard_1.WildcardModel({ uri: req.path, content: '' });
    }
    else {
        console.log(`Wildcard found for path: ${req.path}, deleting it.`);
        yield (wild === null || wild === void 0 ? void 0 : wild.deleteOne());
    }
    res.render('wildcard', { currentPath: req.path, wild: wild });
}));
router.post('*', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    console.log(`POST request received for path: ${req.path}`);
    let wild = yield wildard_1.WildcardModel.findOne({ uri: req.path });
    if (!wild) {
        console.log(`No wildcard found for path: ${req.path}, creating new one with data:`, req.body);
        wild = new wildard_1.WildcardModel(req.body);
        yield wild.save();
    }
    res.render('wildcard-created', { currentPath: req.path, wild: wild });
}));
exports.default = router;
