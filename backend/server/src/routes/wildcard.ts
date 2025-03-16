import express, { Request, Response } from 'express';
import { WildcardModel } from '../models/wildard';

const router = express.Router();

router.get('*', async (req, res) => {
  console.log(`GET request received for path: ${req.path}`);
  let wild = await WildcardModel.findOne({ uri: req.path });
  const empty = !wild;
  if(empty){
    console.log(`No wildcard found for path: ${req.path}, creating new one.`);
    wild = new WildcardModel({ uri: req.path, content: '' });
  }else{
    console.log(`Wildcard found for path: ${req.path}, deleting it.`);
    await wild?.deleteOne();
  }

  res.render('wildcard', { currentPath : req.path, wild:wild }); 
});

router.post('*', async (req, res) => {
  console.log(`POST request received for path: ${req.path}`);
  let wild = await WildcardModel.findOne({ uri: req.path });
  if(!wild){
    console.log(`No wildcard found for path: ${req.path}, creating new one with data:`, req.body);
    wild = new WildcardModel(req.body);
    await wild.save();
  }
  res.render('wildcard-created', { currentPath : req.path, wild: wild }); 
});

export default router;