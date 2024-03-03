import "../styles/home.scss";
import ProductTile from "./productTile";
import { IProduct } from "../interfaces/interfaces";
import { RootState } from "../context/Store";
import { useSelector } from "react-redux";
function Home() {
    const { displayList } = useSelector((state: RootState) => state.products);

    return (
        <div>
            <div className="title">
                <h1 className="kdu">KDU</h1>
                <h1 className="marketplace">MARKETPLACE</h1>
            </div>
            <div className="products">
                {displayList.map((product: IProduct) => {
                    return (
                        <ProductTile
                            key={product.id}
                            productDetails={product}
                        />
                    );
                })}
            </div>
        </div>
    );
}
export default Home;
