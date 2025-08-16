# HUDUnify overlays.json

> [!IMPORTANT]
> WIP

## 基本
ルートにリストがあり、その中にレイヤーを並べて記述していきます。
```json
[
  {
    "name": "example_overlay",
    "widgets": [...]
  },
  {
    "name": "test",
    "widgets": [...]
  },
  {
    "name": "foo",
    "widgets": [...]
  },
]
```

## Layer
ウィジェットを描画するための土台となるものです。  
配置する順番で優先度が変わります。
```json
{
  "name": "example_overlay",
  "widgets": [...]
}
```
`name`: [a-z0-9/._-]+

---
## Widget
なにを描画するかを定義するものです。  
複数のタイプがあります。apiを使用して自作することもできます(ドキュメント準備中)。
### 共通部分
```json
{
  "posX": "0",
  "posY": "0",
  "anchor": "CENTER_TOP",
  "visible": "true"
}
```
すべての値が式に対応しています。  
`anchor`は、以下の値のみ受け付けます
|値|場所|
|:--|:-:|
|LEFT_TOP|左上|
|RIGHT_TOP|右上|
|LEFT_BOTTOM|左下|
|RIGHT_BOTTOM|右下|
|CENTER_TOP|上部中央|
|CENTER_BOTTOM|下部中央|
|LEFT_CENTER|左中央|
|RIGHT_CENTER|右中央|
|CENTER|中央|

`color`プロパティなどの色を入力する箇所では、バニラのTextComponent同様、色名かカラーコードが使用できます。
```json
  "color": "blue"
  "color": "#cf9529"
  "color": "#550084b2"
```

### Text
```json
{
  ...
  "type": "hudunify:text",
  "style": ["BOLD", "UNDERLINE"],
  "color": "#c98ff3",
  "dropShadow": "true",
  "text": "$item('durability', 0)$%"
}
```
テキストを描画するウィジェット。  
`style`と`type`以外のすべての値が式に対応しています。  
`style`は、以下の値のみ受け付けます
|値|装飾|
|:--|:-:|
|NORMAL|無し|
|BOLD|太字|
|ITALIC|斜体|
|UNDERLINE|下線|
|STRIKETHROUGH|打消し線|

### Rectangle
```json
{
  "type": "hudunify:rectangle",
  "width": "24",
  "height": "16",
  "color": "#48c98ff3"
}
```
四角形を描画するウィジェット。  
`type`以外のすべての値が式に対応しています。

### Texture
```json
{
  "type": "hudunify:texture",
  "texture": "minecraft:textures/item/diamond.png"
}
```
テクスチャを描画するウィジェット。  
`type`以外のすべての値が式に対応しています。

### List
```json
{
  "type": "hudunify:list",
  "direction": "HORIZONTAL",
  "padding": "4",
  "widgets": [...]
}
```
`widgets`の中のウィジェットを一方向に並べて描画する特殊なウィジェット。  
`widgets`と`type`以外のすべての値が式に対応しています。  
`direction`は、以下の値のみ対応しています。
|値|向き|
|:--|:-:|
|HORIZONTAL|水平(横方向)|
|VERTICAL|垂直(縦方向)|

---

## 式システム
ほとんどの値に式や関数を埋め込むことができます。  
> プレイヤーの向く角度でy座標が変わり、体力が10未満でリンゴ、それ以外はダイヤを表示するTextureウィジェットのサンプル
```json
{
  "type": "hudunify:texture",
  "posX": "16",
  "posY": "$floor(player('yaw'))$",
  "anchor": "CENTER",
  "visible": "true",
  "texture": "minecraft:textures/item/$if(player('health') < 10, 'apple', 'diamond')$.png"
}
```
[EvalExで用意されている関数](https://ezylang.github.io/EvalEx/references/functions.html)に加えて、以下の関数が使用できます。

### player
```js
player(attribute)
```
`attribute`には、以下の値のみ対応しています。
|属性|説明|
|:--|:--|
|health|体力|
|health_max|最大体力|
|health_percentage|体力の割合|
|pos_x|X座標|
|pos_y|Y座標|
|pos_z|Z座標|
|yaw|垂直方向の角度|
|pitch|水平方向の角度|

例
```js
player('health')
player('pos_x')
```

### item
```js
item(attribute, slotId)
```
`attribute`には、以下の値のみ対応しています。
|属性|説明|
|:--|:--|
|name|アイテム名|
|id|アイテムID|
|count|個数|
|count_max|個数の最大値|
|count_percentage|個数の割合|
|durability|耐久値|
|durability_max|耐久値の最大値|
|durability_percentage|耐久値の割合|
|energy|蓄電量|
|energy_max|蓄電量の最大値|
|energy_percentage|蓄電量の割合|

例
```js
item('name', 4)
item('count', 8)
item('durability', 0)
```
