<template>
	<view class="container">
		<view class="right-top-sign"></view>
		<form @submit="bindLogin">
			<view class="wrapper">
				<view class="left-top-sign">LOGIN</view>
				<view class="input-content">
					<view class="input-item">
						<text class="tit">邮箱</text>
						<input type="text" placeholder="请输入电子邮箱" name="nameValue" />
					</view>
					<view class="input-item">
						<text class="tit">密码</text>
						<input type="password" name="passwordValue" placeholder="密码" placeholder-class="input-empty" />
					</view>
					<view class="input-item">
						<text class="tit">确认密码</text>
						<input type="password" placeholder="确认密码" name="confirmPass" placeholder-class="input-empty" />
					</view>
				</view>
				<button class="confirm-btn" formType="submit" :loading="loading">登录</button>
			</view>
		</form>
		<view class="register-section">
			已经有账号?
			<text @click="toRegist">马上登录</text>
		</view>
	</view>
</template>

<script>
	import helps from '../../../common/util.js'
	import {
		mapMutations
	} from 'vuex';

	export default {
		data() {
			return {
				loading: false
			}
		},
		methods: {
			toRegist() {
				uni.navigateTo({
					url: '/pages/public/login'
				})
			},
			bindLogin(e) {
				this.loading = true;
				let name = e.detail.value.nameValue,
					password = e.detail.value.passwordValue,
					comfirmP = e.detail.value.confirmPass;
				if (!helps.isEmail(name)) {
					this.$api.msg('请输入正确的邮箱格式');
					return;
				}
				if (comfirmP != password) {
					this.$api.msg('密码与确认密码不一致');
					return;
				}
				let newUser = new Object();
				newUser.loginName = name;
				newUser.password = password;
				uni.request({
					url: helps.getUrl() + 'user/register',
					data: newUser,
					method: "POST",
					success: (e) => {
						console.info(e)
						if (e.data.code !== 200) {
							uni.showModal({
								content: "用户名密码错误！",
								showCancel: false
							});
							return;
						}
						if (e.data.code === 200) {
							this.login(e.data.data.userInfo);
							uni.setStorage({
								key: 'accessToken',
								data: e.data.data.accessToken
							})
							uni.switchTab({
								url: '../index/index'
							});
						} else {
							uni.showModal({
								content: e.data.message,
								showCancel: false
							})
						}
					},
					fail: (e) => {
						uni.showModal({
							content: "请求失败，请重试！",
							showCancel: false
						})
					},
					complete: () => {
						this.loading = false;
					}
				})
			},
			...mapMutations(['login'])
		}
	}
</script>

<style lang='scss'>
	page {
		background: #fff;
	}

	.container {
		position: relative;
		width: 100vw;
		height: 100vh;
		overflow: hidden;
		background: #fff;
	}

	.wrapper {
		position: relative;
		z-index: 90;
		background: #fff;
		padding-bottom: 40upx;
	}

	.back-btn {
		position: absolute;
		left: 40upx;
		z-index: 9999;
		padding-top: var(--status-bar-height);
		top: 40upx;
		font-size: 40upx;
		color: $font-color-dark;
	}

	.left-top-sign {
		font-size: 120upx;
		color: $page-color-base;
		position: relative;
		left: -16upx;
	}

	.right-top-sign {
		position: absolute;
		top: 80upx;
		right: -30upx;
		z-index: 95;

		&:before,
		&:after {
			display: block;
			content: "";
			width: 400upx;
			height: 80upx;
			background: #b4f3e2;
		}

		&:before {
			transform: rotate(50deg);
			border-radius: 0 50px 0 0;
		}

		&:after {
			position: absolute;
			right: -198upx;
			top: 0;
			transform: rotate(-50deg);
			border-radius: 50px 0 0 0;
			/* background: pink; */
		}
	}

	.left-bottom-sign {
		position: absolute;
		left: -270upx;
		bottom: -320upx;
		border: 100upx solid #d0d1fd;
		border-radius: 50%;
		padding: 180upx;
	}

	.welcome {
		position: relative;
		left: 50upx;
		top: -90upx;
		font-size: 46upx;
		color: #555;
		text-shadow: 1px 0px 1px rgba(0, 0, 0, .3);
	}

	.input-content {
		padding: 0 60upx;
	}

	.input-item {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
		padding: 0 30upx;
		background: $page-color-light;
		height: 120upx;
		border-radius: 4px;
		margin-bottom: 50upx;

		&:last-child {
			margin-bottom: 0;
		}

		.tit {
			height: 50upx;
			line-height: 56upx;
			font-size: $font-sm+2upx;
			color: $font-color-base;
		}

		input {
			height: 60upx;
			font-size: $font-base + 2upx;
			color: $font-color-dark;
			width: 100%;
		}
	}

	.confirm-btn {
		width: 630upx;
		height: 76upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 70upx;
		background: $uni-color-primary;
		color: #fff;
		font-size: $font-lg;

		&:after {
			border-radius: 100px;
		}
	}

	.forget-section {
		font-size: $font-sm+2upx;
		color: $font-color-spec;
		text-align: center;
		margin-top: 40upx;
	}

	.register-section {
		position: absolute;
		left: 0;
		width: 100%;
		font-size: $font-sm+2upx;
		color: $font-color-base;
		text-align: center;

		text {
			color: $font-color-spec;
			margin-left: 10upx;
		}
	}
</style>
