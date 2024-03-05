import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useParams } from "react-router-dom";
import { IDisplayTransaction, ILiveTrade, IStock } from "../interfaces/interfaces";
import TradeSection from "../components.tsx/tradeSection";
import HistorySection from "../components.tsx/historySection";
import { createUseStyles } from "react-jss";
import { useEffect, useState } from "react";
import { Socket } from "socket.io-client";

function Stock({socket}:Readonly<{socket:Socket}>) {
    const { stockList } = useSelector((state: RootState) => state.stocks);
    console.log(stockList);
    const { id } = useParams();
    const stock: IStock = stockList.filter(
        (stock: IStock) => stock.id.toString() == id!.toString()
    )[0];
    useEffect(()=>{
        socket.emit('join',id)
    },[])
    const [trades, setTrades] = useState<IDisplayTransaction[]>([]);
    const newTransaction = (trade: IDisplayTransaction) => {
        setTrades((previous) => [...previous, trade]);
    };
    const userStyles = createUseStyles({
        stockPage: {
            display: "flex",
            flexGrow: 1,
        },
    });
    const classes = userStyles();
    const [liveTrades, setLiveTrades] = useState<ILiveTrade[]>([])
    function newTrade(data: any) {
        setLiveTrades([...liveTrades, data]);
    }
    socket.on("new-trade", (payload) => newTrade(payload));
    return (
        <div className={classes.stockPage}>
            <TradeSection stock={stock} newTransaction={newTransaction} socket={(socket)}/>
            <HistorySection trades={trades} liveTrades={liveTrades}/>
        </div>
    );
}
export default Stock;
