import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useParams } from "react-router-dom";
import { IDisplayTransaction, IStock } from "../interfaces/interfaces";
import TradeSection from "../components.tsx/tradeSection";
import HistorySection from "../components.tsx/historySection";
import { createUseStyles } from "react-jss";
import { useState } from "react";

function Stock(){
    const { stockList } = useSelector(
        (state: RootState) => state.stocks
    );
    console.log(stockList)
    const { id } = useParams();
    const stock: IStock  = stockList.filter(
        (stock: IStock) => stock.id.toString() == id!.toString()
    )[0];
    const [trades, setTrades] = useState<IDisplayTransaction[]>([])
    const newTransaction = (trade: IDisplayTransaction) => {
        setTrades((previous) => [...previous, trade])
    }
    const userStyles = createUseStyles({
        stockPage: {
            display: 'flex',
            flexGrow: 1
        }
    })
    const classes = userStyles();
    return(
        <div className={classes.stockPage}>
            <TradeSection stock={stock} newTransaction={newTransaction}/>
            <HistorySection trades={trades}/>
        </div>
    )
}
export default Stock;