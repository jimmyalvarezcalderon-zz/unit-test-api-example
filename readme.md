# Work Shop Unit Testing

This project makes part of an internal presentation focused on Unit testing, feel free to fork and play with it.

## App Description 
App used to buy items on a store, here we have lot of discounts you can enjoy.


| Features  |
| ------------- |
| Create an invoice with the selected products  |
| Calculate price applying each product's discount  |
| Calculate total applying coupon discount  |
| Calculate total subtracting products prices from user cash  |

## How to buy
```
curl --location --request GET 'http://127.0.0.1:5000/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "products": [
        1,
        3
    ],
    "cash": 50000,
    "coupon": "YYYYY"
}'
```

## Test cases


| Tests  |
| ------------- |
|When the user buys a product that is not in the stock, then this product price is not subtracted from user cash
|When the user buys a product available in stock that has %20  of discount, then discount should be subtracted from product price.
|When the user does a valid buy, then an invoice should be generated
|When the user buys with a coupon, then coupon value should be subtracted from total price
|When the user buys a product with no cash, then buy should be canceled
|When the user buys a product, products units in stock should decrease
|When the user buys a product with an invalid coupon, then no coupon discount should be applied to total
|When the user buys more units than the available in stock, then only the available units should be taken into the total calculation

