import { useSelector } from "react-redux";
import { RootState } from "../redux/store";

function Portfolio(){
    const { trades } = useSelector((state: RootState) => state.stocks);

    return(
        <></>
    )
}
export default Portfolio;