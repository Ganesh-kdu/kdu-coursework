import express from 'express';
import bodyParser from 'body-parser';

const app = express();
const PORT = 5000;

app.use(bodyParser.json());

let messages = [];

app.post('/messages', (req, res) => {
    console.log(req.body);
    const { author, message } = req.body;
    const newMessage = {
        id: messages.length,
        time: new Date(),
        author,
        message
    };
    messages.push(newMessage);
    res.status(201).json(newMessage);
});

app.get('/messages', (req, res) => {
    res.json(messages);
});

app.get('/messages/:id', (req, res) => {
    const messageId = parseInt(req.params.id);
    if(messageId>=messages.length) {
        res.status(404).json({ error: 'Message not found' });
    } else {
        res.json(messages[messageId]);
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
