import express from "express";
import todoRouter from "./routes/todo";

const app = express();
const port = 8080;


// Plugins
app.use(express.json());

// Routes
app.use("/todo", todoRouter);

//

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
})

module.exports = app;