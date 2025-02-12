import express, {Request, Response, Errback, NextFunction} from "express";
import todoRouter from "./routes/todoRouter";
import {connectToDatabase} from "./db/database";

const app = express();
const port = 8080;

// DB

connectToDatabase().then(() => console.log("Connected to DB"));
// Middlewares
app.use(express.json());


// Routes
app.use("/todo", todoRouter);

//

app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
})

module.exports = app;