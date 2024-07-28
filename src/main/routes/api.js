const express = require('express');
const router = express.Router();

/**
 * @swagger
 * /api/data:
 *   get:
 *     summary: Retrieve a message
 *     responses:
 *       200:
 *         description: A simple message
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 message:
 *                   type: string
 *                   example: This is your open API endpoint!
 */
router.get('/data', (req, res) => {
  res.json({ message: 'This is your open API endpoint!' });
});

module.exports = router;

