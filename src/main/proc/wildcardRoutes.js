const { RMPathKey, RMPathTextContent } = require('../models/models');

module.exports = (app) => {
    app.get('*', async (req, res, next) => {
        if (res.headersSent) {
            return next();
        }
        try {
            const cl1p = await RMPathKey.findOne({ uri: req.url });
            if (!cl1p) {
                const data = { cl1pPath: req.url, message: 'See' };
                res.render('cl1p_view', data);
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
};