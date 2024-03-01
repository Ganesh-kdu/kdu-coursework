import { useContext } from "react";
import "../styles/home.scss";
import ProductTile from "./productTile";
import { IProduct } from "../interfaces/interfaces";
import { ECommerceContext } from "../context/ProductContext";
function Home() {
    const { displayList } = useContext(ECommerceContext);
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
