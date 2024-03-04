import { useState, useEffect } from "react";
import { createUseStyles } from "react-jss";
import { ISummarizerStock, IWorkerResult } from "../interfaces/interfaces";

function Summarizer() {
    const [maxProfit, setMaxProfit] = useState<IWorkerResult[]>([]);
    const [stocks, setStocks] = useState<ISummarizerStock[]>([]);
    async function getStocks() {
        const response = await fetch(
            "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json"
        );
        const data = await response.json();
        setStocks(data);
    }
    useEffect(() => {
        getStocks();
    }, []);
    useEffect(() => {
        const workers: Worker[] = [];
        stocks.forEach((stock, index) => {
            const worker = new Worker(new URL("./worker.tsx", import.meta.url));
            worker.postMessage({ stock, index });
            worker.onmessage = (event) => {
                setMaxProfit((prevMaxProfit: IWorkerResult[]) => [
                    ...prevMaxProfit,
                    event.data,
                ]);
            };
            workers.push(worker);
        });

        return () => {
            workers.forEach((worker) => worker.terminate());
        };
    }, [stocks]);
    const classes = createUseStyles({
        container: {
            display: "flex",
            justifyContent: "space-between",
            backgroundColor: "#2F7EC7",

            borderRadius: "20px",
            padding: "20px",
            margin: "10px",
        },
        pair: {
            display: "flex",
            justifyContent: "space-between",
            flexDirection: "column",
        },
        big: { color: "white", padding: 0, margin: 0, fontSize: '30px'},
        small: { color: "white"},
    })();
    return (
        <div>
            {maxProfit.map((profit: IWorkerResult, index: number) => (
                <div key={profit.profit} className={classes.container}>
                    <div className={classes.pair}>
                        <h2 className={classes.big}>{stocks[index].company}</h2>
                        <p className={classes.small}>Profit margin: {profit.profit}</p>
                    </div>
                    <div>
                        <p className={classes.small}>
                            Buy: {profit.buyPrice} on {profit.buyDate}
                        </p>
                        <p className={classes.small}>
                            Sell: {profit.sellPrice} on {profit.sellDate}
                        </p>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default Summarizer;
