import { IProduct } from "../interfaces/interfaces";
import "../styles/product.scss"
function ProductTile({productDetails}:Readonly<{productDetails:IProduct}>) {
    return(
        <div>{productDetails.title}</div>
    )
}
export default ProductTile;