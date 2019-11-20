<template>
	<view class="content">
		<view class="row b-b">
			<text class="tit">名称</text>
			<input class="input" type="text" v-model="productData.name" placeholder="名称" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">类别</text>
			<view class="uni-list-cell">
				<view class="uni-list-cell-db">
					<picker @change="bindPickerChange" :value="index" :range="array" range-key="name">
						<view class="uni-input" :value="array[index].id">{{array[index].name}}</view>
					</picker>
				</view>
			</view>
		</view>
		<view class="row b-b">
			<text class="tit">现价</text>
			<input class="input" type="number" v-model="productData.price" placeholder="现在的价格" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">原价</text>
			<input class="input" type="number" v-model="productData.oldPrice" placeholder="原价" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">交换物品</text>
			<input class="input" type="text" v-model="productData.changeWhat" placeholder="需要交换的物品" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">数量</text>
			<input class="input" type="text" v-model="productData.quantity" placeholder="数量" placeholder-class="placeholder" />
		</view>
		<view class="row b-b" style="height: 300upx;">
			<image :src="productData.file" style="	display: block; width: 210upx; height: 210upx;"></image>
			<button type="primary" :loading="loading" :disabled="disabled" @click="upload">选择照片</button>
		</view>
		<button class="add-btn" @click="confirm">提交</button>
	</view>
</template>

<script>
	import helps from '../../../common/util.js';
	export default {
		data() {
			return {
				productData: {
					name: '',
					quantity: 0,
					price: 0,
					oldPrice: 0,
					changeWhat: '',
					file: '',
					category: ''
				},
				index: 0,
				array: [],
				percent: 0,
				loading: false,
				disabled: false,
				token: ''
			}
		},
		onLoad(option) {
			let title = '上架商品';
			var _this = this;
			if (option.type === 'edit') {
				title = '编辑收货地址'
				_this.productData = JSON.parse(option.data)
			}
			uni.request({
				url: helps.getUrl() + 'category',
				method: 'GET',
				success: function(res) {
					console.info(res)
					for (let row in res.data.data.rows) {
						if (res.data.data.rows[row].img != null && res.data.data.rows[row].img != '') {
							_this.array.push(res.data.data.rows[row]);
						}
					}
					_this.productData.category = _this.array[0].id;
				},
				fail: function(err) {
					console.info(err)
				}
			})
			_this.manageType = option.type;
			uni.setNavigationBarTitle({
				title
			})
		},
		methods: {
			onClick() {
				uni.showToast({
					title: "click van-button",
					mask: false,
					icon: "none",
					duration: 1500
				});

			},
			switchChange(e) {
				this.productData.default = e.detail;
			},
			upload() {
				let _this = this;
				uni.chooseImage({
					count: 1,
					sizeType: ['original'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
						_this.productData.file = res.tempFilePaths[0]
					},
					error: function(e) {
						console.log(e);
					}
				});
			},
			//地图选择地址
			chooseLocation() {
				uni.chooseLocation({
					success: (data) => {
						this.productData.addressName = data.name;
						this.productData.address = data.name;
					}
				})
			},
			bindPickerChange: function(e) {
				let _this = this;
				_this.index = e.target.value
				_this.productData.category = _this.array[_this.index].id;
			},
			//提交
			confirm() {
				let data = this.productData;
				if (!data.name) {
					this.$api.msg('请填写商品名称');
					return;
				}
				if (!data.file) {
					this.$api.msg('选择一张图片');
					return;
				}
				let _this = this;
				uni.getStorage({
					key: 'accessToken',
					success(e) {
						_this.token = e.data
					}
				})
				uni.uploadFile({
					url: helps.getUrl() + 'product',
					filePath: data.file, // uni.chooseImage函数调用后获取的本地文件路劲
					name: 'file', //后端通过'file'获取上传的文件对象
					formData: data,
					header: {
						accessToken: _this.token
					},
					success: (res) => {
						console.info(res)
						if (res.statusCode == 200) {
							this.$api.msg('上架成功');
							uni.navigateTo({
								url: '../my_pro'
							})
						}
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	page {
		background: $page-color-base;
		padding-top: 16upx;
	}

	.row {
		display: flex;
		align-items: center;
		position: relative;
		padding: 0 30upx;
		height: 110upx;
		background: #fff;

		.tit {
			flex-shrink: 0;
			width: 120upx;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.input {
			flex: 1;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.icon-shouhuodizhi {
			font-size: 36upx;
			color: $font-color-light;
		}
	}

	.default-row {
		margin-top: 16upx;

		.tit {
			flex: 1;
		}

		switch {
			transform: translateX(16upx) scale(.9);
		}
	}

	.add-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		margin: 60upx auto;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
