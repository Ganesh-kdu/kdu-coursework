import "../styles/product.scss";
import { IProduct } from "../interfaces/interfaces";
import { Link, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "../context/Store";
function Product() {
    const { displayList } = useSelector((state: RootState) => state.products);
    const { id } = useParams();
    const product: IProduct = displayList.filter(
        (product: IProduct) => product.id.toString() == id!.toString()
    )[0];

    return (
        <div className="product-page">
            <div className="product-title">{product.title}</div>
            <div className="product-container">
                <img src={product.image} alt="" className="product-image" />
                <div className="details">
                    <div className="title">Model: {product.title}</div>
                    <div className="price">Price : $ {product.price}</div>
                    <div className="description">Product Description</div>
                    <div className="desc-content">{product.description}</div>
                    <Link to="/">
                        <button>Back To Products</button>
                    </Link>
                </div>
            </div>
        </div>
    );
}
export default Product;
