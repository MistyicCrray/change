<template>
	<view>
		<view class="goods-section">
			<view class="g-header b-b">
				<text class="name">{{product.user.userName}}</text>
			</view>
			<view class="g-item">
				<image :src="image + product.img"></image>
				<view class="right">
					<text class="title clamp">{{product.name}}</text>
					<text class="spec">{{product.category2.name}}</text>
					<view class="price-box">
						<text class="price">￥{{product.price}}</text>
					</view>
				</view>
			</view>
		</view>

		<view class="yt-list" v-if="type==1">
			<view class="yt-list-cell b-b" @click="toggleMask('show')">
				<text class="cell-tit clamp">交换物品</text>
				<text class="cell-tip active" v-if="changePro == ''">
					选择交换物品
				</text>
				<text class="cell-tip active" v-if="changePro != ''">
					{{changePro.name}}
				</text>
				<text class="cell-more wanjia wanjia-gengduo-d"></text>
			</view>
		</view>
		<view class="yt-list">
			<view class="yt-list-cell b-b">
				<text class="cell-tit clamp">商品金额</text>
				<text class="cell-tip">￥{{product.price}}</text>
			</view>
			<view class="yt-list-cell b-b">
				<text class="cell-tit clamp">运费</text>
				<text class="cell-tip">免运费</text>
			</view>
			<view class="yt-list-cell desc-cell">
				<text class="cell-tit clamp">备注</text>
				<input class="desc" type="text" v-model="desc" placeholder="请填写备注信息" placeholder-class="placeholder" />
			</view>
		</view>

		<view class="footer">
			<view class="price-content">
				<text>实付款</text>
				<text class="price-tip">￥</text>
				<text class="price">0</text>
			</view>
			<text class="submit" @click="submit(0)" v-if="type==0">提交订单</text>
			<text class="submit" @click="submit(1)" v-if="type==1">提交交换申请</text>
		</view>

		<view class="mask" :class="maskState===0 ? 'none' : maskState===1 ? 'show' : ''" @click="toggleMask">
			<view class="mask-content" @click.stop.prevent="stopPrevent">
				<view class="coupon-item" v-for="(item,index) in couponList" :key="index">
					<view class="con" @click="chooseChange(item)">
						<view class="left">
							<image style="width: 140upx;height: 140upx;" :src="image + item.img"></image>
						</view>
						<view class="right">
							<text class="price">{{item.price}}</text>
							<text>{{item.name}}</text>
						</view>
						<view class="circle l"></view>
						<view class="circle r"></view>
					</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import helps from '../../common/util.js';
	export default {
		data() {
			return {
				maskState: 0, //优惠券面板显示状态
				desc: '', //备注
				payType: 1, //1微信 2支付宝
				productId: "",
				type: '',
				seller: "",
				product: "",
				image: helps.imgUrl(),
				couponList: [],
				token: '',
				changePro: "",
				paramData: {},
				addressData: {
					name: '许小星',
					mobile: '13853989563',
					addressName: '金九大道',
					address: '山东省济南市历城区',
					area: '149号',
					default: false,
				}
			}
		},
		onLoad(option) {
			let _this = this;
			_this.productId = option.productId;
			_this.type = option.type;
			uni.getStorage({
				key: 'accessToken',
				success(e) {
					_this.token = e.data
				}
			})
			console.info(_this.type + _this.productId);
			uni.request({
				url: helps.getUrl() + "product/" + _this.productId,
				method: "GET",
				success(res) {
					console.info(res)
					_this.product = res.data.data;
				}
			})
			uni.request({
				url: helps.getUrl() + "product/getByUser",
				method: "GET",
				header: {
					accessToken: _this.token
				},
				success(res) {
					console.info(res)
					_this.couponList = res.data.data.rows;
				}
			})
		},
		methods: {
			//显示优惠券面板
			toggleMask(type) {
				let timer = type === 'show' ? 10 : 300;
				let state = type === 'show' ? 1 : 0;
				this.maskState = 2;
				setTimeout(() => {
					this.maskState = state;
				}, timer)
			},
			numberChange(data) {
				this.number = data.number;
			},
			changePayType(type) {
				this.payType = type;
			},
			chooseChange(pro) {
				let _this = this;
				console.info(pro)
				_this.changePro = pro;
			},
			submit() {
				let _this = this;
				if (_this.type == 1) {
					if (_this.changePro == '') {
						_this.$api.msg(`请选择要交换的物品`);
					}
					_this.paramData.curProId = _this.productId;
					_this.paramData.changeProId = _this.changePro.id;
					uni.request({
						url: helps.getUrl() + 'product/changePro',
						method: "POST",
						header: {
							accessToken: _this.token
						},
						data: _this.paramData,
						success(res) {
							if (res.data.code == 200) {
								_this.$api.msg("交换申请成功");
							} else {
								_this.$api.msg(res.data.message);
							}
						},
						fail(err) {
							console.info(err)
						}
					});
				} else {
					let products = _this.product;
					// uni.request({
					// 	url: helps.getUrl() + 'product/buy',
					// 	method: "POST",
					// 	header: {
					// 		accessToken: _this.token
					// 	},
					// 	data: JSON.parse(JSON.stringify(_this.product)),
					// 	success(res) {
					// 		console.info(res);
					// 	},
					// 	fail(err) {
					// 		console.info(err);
					// 	}
					// });0
					let obj = {
						appid: '2019112169318536',
						noncestr: '222',
						package: 'Sign=WXPay', // 固定值，以微信支付文档为主  
						partnerid: '商户号',
						prepayid: '预支付交易会话',
						timestamp: '时间戳',
						sign: '签名' // 根据签名算法生成签名  
					}
					// 第二种写法，传对象字符串  
					let orderInfo = JSON.stringify(obj);
					uni.requestPayment({
						provider: 'wxpay',
						orderInfo: orderInfo, //微信、支付宝订单数据  
						success: function(res) {
							console.log('success:' + JSON.stringify(res));
						},
						fail: function(err) {
							console.log('fail:' + JSON.stringify(err));
						}
					});
				}
			},
			stopPrevent() {}
		}
	}
</script>

<style lang="scss">
	page {
		background: $page-color-base;
		padding-bottom: 100upx;
	}

	.address-section {
		padding: 30upx 0;
		background: #fff;
		position: relative;

		.order-content {
			display: flex;
			align-items: center;
		}

		.icon-shouhuodizhi {
			flex-shrink: 0;
			display: flex;
			align-items: center;
			justify-content: center;
			width: 90upx;
			color: #888;
			font-size: 44upx;
		}

		.cen {
			display: flex;
			flex-direction: column;
			flex: 1;
			font-size: 28upx;
			color: $font-color-dark;
		}

		.name {
			font-size: 34upx;
			margin-right: 24upx;
		}

		.address {
			margin-top: 16upx;
			margin-right: 20upx;
			color: $font-color-light;
		}

		.icon-you {
			font-size: 32upx;
			color: $font-color-light;
			margin-right: 30upx;
		}

		.a-bg {
			position: absolute;
			left: 0;
			bottom: 0;
			display: block;
			width: 100%;
			height: 5upx;
		}
	}

	.goods-section {
		margin-top: 16upx;
		background: #fff;
		padding-bottom: 1px;

		.g-header {
			display: flex;
			align-items: center;
			height: 84upx;
			padding: 0 30upx;
			position: relative;
		}

		.logo {
			display: block;
			width: 50upx;
			height: 50upx;
			border-radius: 100px;
		}

		.name {
			font-size: 30upx;
			color: $font-color-base;
			margin-left: 24upx;
		}

		.g-item {
			display: flex;
			margin: 20upx 30upx;

			image {
				flex-shrink: 0;
				display: block;
				width: 140upx;
				height: 140upx;
				border-radius: 4upx;
			}

			.right {
				flex: 1;
				padding-left: 24upx;
				overflow: hidden;
			}

			.title {
				font-size: 30upx;
				color: $font-color-dark;
			}

			.spec {
				font-size: 26upx;
				color: $font-color-light;
			}

			.price-box {
				display: flex;
				align-items: center;
				font-size: 32upx;
				color: $font-color-dark;
				padding-top: 10upx;

				.price {
					margin-bottom: 4upx;
				}

				.number {
					font-size: 26upx;
					color: $font-color-base;
					margin-left: 20upx;
				}
			}

			.step-box {
				position: relative;
			}
		}
	}

	.yt-list {
		margin-top: 16upx;
		background: #fff;
	}

	.yt-list-cell {
		display: flex;
		align-items: center;
		padding: 10upx 30upx 10upx 40upx;
		line-height: 70upx;
		position: relative;

		&.cell-hover {
			background: #fafafa;
		}

		&.b-b:after {
			left: 30upx;
		}

		.cell-icon {
			height: 32upx;
			width: 32upx;
			font-size: 22upx;
			color: #fff;
			text-align: center;
			line-height: 32upx;
			background: #f85e52;
			border-radius: 4upx;
			margin-right: 12upx;

			&.hb {
				background: #ffaa0e;
			}

			&.lpk {
				background: #3ab54a;
			}

		}

		.cell-more {
			align-self: center;
			font-size: 24upx;
			color: $font-color-light;
			margin-left: 8upx;
			margin-right: -10upx;
		}

		.cell-tit {
			flex: 1;
			font-size: 26upx;
			color: $font-color-light;
			margin-right: 10upx;
		}

		.cell-tip {
			font-size: 26upx;
			color: $font-color-dark;

			&.disabled {
				color: $font-color-light;
			}

			&.active {
				color: $base-color;
			}

			&.red {
				color: $base-color;
			}
		}

		&.desc-cell {
			.cell-tit {
				max-width: 90upx;
			}
		}

		.desc {
			flex: 1;
			font-size: $font-base;
			color: $font-color-dark;
		}
	}

	/* 支付列表 */
	.pay-list {
		padding-left: 40upx;
		margin-top: 16upx;
		background: #fff;

		.pay-item {
			display: flex;
			align-items: center;
			padding-right: 20upx;
			line-height: 1;
			height: 110upx;
			position: relative;
		}

		.icon-weixinzhifu {
			width: 80upx;
			font-size: 40upx;
			color: #6BCC03;
		}

		.icon-alipay {
			width: 80upx;
			font-size: 40upx;
			color: #06B4FD;
		}

		.icon-xuanzhong2 {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 60upx;
			height: 60upx;
			font-size: 40upx;
			color: $base-color;
		}

		.tit {
			font-size: 32upx;
			color: $font-color-dark;
			flex: 1;
		}
	}

	.footer {
		position: fixed;
		left: 0;
		bottom: 0;
		z-index: 995;
		display: flex;
		align-items: center;
		width: 100%;
		height: 90upx;
		justify-content: space-between;
		font-size: 30upx;
		background-color: #fff;
		z-index: 998;
		color: $font-color-base;
		box-shadow: 0 -1px 5px rgba(0, 0, 0, .1);

		.price-content {
			padding-left: 30upx;
		}

		.price-tip {
			color: $base-color;
			margin-left: 8upx;
		}

		.price {
			font-size: 36upx;
			color: $base-color;
		}

		.submit {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 280upx;
			height: 100%;
			color: #fff;
			font-size: 32upx;
			background-color: $base-color;
		}
	}

	/* 优惠券面板 */
	.mask {
		display: flex;
		align-items: flex-end;
		position: fixed;
		left: 0;
		top: var(--window-top);
		bottom: 0;
		width: 100%;
		background: rgba(0, 0, 0, 0);
		z-index: 9995;
		transition: .3s;

		.mask-content {
			width: 100%;
			min-height: 30vh;
			max-height: 70vh;
			background: #f3f3f3;
			transform: translateY(100%);
			transition: .3s;
			overflow-y: scroll;
		}

		&.none {
			display: none;
		}

		&.show {
			background: rgba(0, 0, 0, .4);

			.mask-content {
				transform: translateY(0);
			}
		}
	}

	/* 优惠券列表 */
	.coupon-item {
		display: flex;
		flex-direction: column;
		margin: 20upx 24upx;
		background: #fff;

		.con {
			display: flex;
			align-items: center;
			position: relative;
			height: 120upx;
			padding: 0 30upx;

			&:after {
				position: absolute;
				left: 0;
				bottom: 0;
				content: '';
				width: 100%;
				height: 0;
				border-bottom: 1px dashed #f3f3f3;
				transform: scaleY(50%);
			}
		}

		.left {
			display: flex;
			flex-direction: column;
			justify-content: center;
			flex: 1;
			overflow: hidden;
			height: 100upx;
		}

		.title {
			font-size: 32upx;
			color: $font-color-dark;
			margin-bottom: 10upx;
		}

		.time {
			font-size: 24upx;
			color: $font-color-light;
		}

		.right {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			font-size: 26upx;
			color: $font-color-base;
			height: 100upx;
		}

		.price {
			font-size: 44upx;
			color: $base-color;

			&:before {
				content: '￥';
				font-size: 34upx;
			}
		}

		.tips {
			font-size: 24upx;
			color: $font-color-light;
			line-height: 60upx;
			padding-left: 30upx;
		}

		.circle {
			position: absolute;
			left: -6upx;
			bottom: -10upx;
			z-index: 10;
			width: 20upx;
			height: 20upx;
			background: #f3f3f3;
			border-radius: 100px;

			&.r {
				left: auto;
				right: -6upx;
			}
		}
	}
</style>
