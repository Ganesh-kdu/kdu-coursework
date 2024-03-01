import { Link } from "react-router-dom";
import { IProduct } from "../interfaces/interfaces";
import "../styles/product.scss";
function ProductTile({
    productDetails,
}: Readonly<{ productDetails: IProduct }>) {
    return (
        <Link to={`/${productDetails.id}`}>
            <div className="tile">
                <img
                    src={`${productDetails.image}`}
                    className="product-image"
                    alt="Product"
                ></img>
                <div className="basic-details">
                    <div className="product-title">
                        {productDetails.title.substring(0, 45)}...
                    </div>
                    <div className="product-price">${productDetails.price}</div>
                </div>
            </div>
        </Link>
    );
}
export default ProductTile;
