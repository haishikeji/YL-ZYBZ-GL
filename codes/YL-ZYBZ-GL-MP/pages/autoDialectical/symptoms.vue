<template>
	<view class="symptom">
		<cu-custom bgColor="bg-white" :isBack="true">
			<block slot="content">
				智能辩证
			</block>
		</cu-custom>
		<view class="m-title">
			<view class="bt1">智能辩证</view>
			<view class="bt1">
				<uni-badge class="uni-badge-left-margin" text="1" type="primary"
					:customStyle="{background: '#5dbc8b'}" /><text class="current">病症—</text>
				<uni-badge class="uni-badge-left-margin" text="2" type="primary"
					:customStyle="{background: '#5dbc8b'}" /><text class="current">辩证—</text>
				<uni-badge class="uni-badge-left-margin" text="3" type="primary"
					:customStyle="{background: '#adadad'}" /><text style="color: #adadad;">施治</text>
			</view>
		</view>
		<view class="describle">
			<view class="title"><text style="color: #5ac725;font-weight: 700;font-size: 18px;">{{name}}</text>
				请选择患者症状</view>
		</view>
		<scroll-view scroll-y="true" :scroll-top="0" style="height: 100vh;">
			<view v-for="(item,index) in list" :key="index" class="btn-info">
				<view>{{item.letter}}</view>
				<button plain="true" type="default" size="mini" v-for="(btnItem,i) in item.data"
					@click="btnClick(btnItem)" :id="[btnItem.check?'item':'']">{{btnItem.name}}
				</button>

			</view>
		</scroll-view>
		<button type="default" class="result" @click="goResult">计算证型</button>
	</view>

</template>

<script>
	import BzDiseaseManageService from "@/api/bz/BzDiseaseManageService"
	import {
		pinyin
	} from './pinyin.js'
	export default {
		onLoad() {
			let option = uni.getStorageSync("symptom");
			this.name = option.name
			new BzDiseaseManageService().list({
				diseaseType: {
					id: option.id
				},
			}).then(data => {
				let disease = data.data.records
				let symptom = []

				disease.forEach(item => {
					item.bzDiseaseSymptomDTOList.forEach(item1 => {
						let obj = {
							id: item1.symptom.id,
							name: item1.symptom.name
						}
						symptom.push(obj)
					})
				})
				let map = new Map();
				for (let item of symptom) {
					if (!map.has(item.id)) {
						map.set(item.id, item);
					};
				};
				symptom = [...map.values()];
				this.list = this.pySegSort(symptom);
			})

		},
		data() {
			return {
				name: "",
				list: [],
				result: []
			}
		},
		methods: {
			btnClick(item) {
				item.check = !item.check
				if (item.check) {
					this.result.push(item)
				}
			},
			goResult() {
				if (this.result.length <= 0) {
					uni.showToast({
						icon: "error",
						title: "选择症状"
					})
				} else {
					let checks = this.result.filter(item => item.check)
					uni.navigateTo({
						url: "/pages/autoDialectical/cureResult?obj=" + encodeURIComponent(
							JSON.stringify(checks))
					})
				}
			},
			// 拼音排序
			pySegSort(arr, empty) {
				var letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split('');
				var segs = [];
				var curr;
				letters.forEach((item, i) => {
					let cur = {
						letter: item,
						data: []
					}
					arr.forEach((item1) => {
						let py = this.chineseToInitials(this.chineseToPinYin(item1.name)).split("")
						if (py[0] == item) {
							let a = {
								id: item1.id,
								name: item1.name,
								check: false
							}
							cur.data.push(a)
						}
					})
					if (cur.data.length) {
						cur.data.sort(function(a, b) {
							return a.name.localeCompare(b.name, 'zh-CN')
						})
						segs.push(cur)
					}
				})
				return segs;
			},
			chineseToInitials(word) {
				let SX = '';
				for (var i = 0; i < word.length; i++) {
					var c = word.charAt(i);
					if (/^[A-Z]+$/.test(c)) {
						SX += c;
					}
				}
				return SX
			},
			chineseToPinYin(l1) {
				var l2 = l1.length;
				var I1 = '';
				var reg = new RegExp('[a-zA-Z0-9]');
				for (var i = 0; i < l2; i++) {
					var val = l1.substr(i, 1);
					var name = this.arraySearch(val, pinyin);
					if (reg.test(val)) {
						I1 += val;
					} else if (name !== false) {
						I1 += name;
					}
				}
				I1 = I1.replace(/ /g, '-');
				while (I1.indexOf('--') > 0) {
					I1 = I1.replace('--', '-');
				}
				return I1;
			},
			arraySearch(l1, l2) {
				for (var name in pinyin) {
					if (pinyin[name].indexOf(l1) !== -1) {
						return this.ucfirst(name);
					}
				}
				return false;
			},
			ucfirst(l1) {
				if (l1.length > 0) {
					var first = l1.substr(0, 1).toUpperCase();
					var spare = l1.substr(1, l1.length);
					return first + spare;
				}
			},
		}
	}
</script>

<style lang="scss" scoped>
	.m-title {
		display: flex;
		background-color: white;
		padding: 8rpx;
		margin-bottom: 30rpx;
	}

	.symptom {
		padding: 10px;
	}

	.list-cell {
		display: flex;
		box-sizing: border-box;
		width: 100%;
		padding: 10px 24rpx;
		overflow: hidden;
		color: #323233;
		font-size: 14px;
		line-height: 24px;
		background-color: #fff;
	}

	.line {
		height: 0;
		width: 100%;
		border: 1px solid #4ebd71;
	}

	.btn-info {
		overflow: hidden;
		margin: 20rpx;
		border-bottom: 2px solid #959595;
	}

	.btn-info button {
		float: left;
		margin: 10rpx;
	}

	.btn-info #item {
		background-color: #4ebd71;
		color: #fff;
		border-color: #4ebd71;
	}

	.bt1 {
		border-radius: 20rpx;
		padding: 16rpx 30rpx;
		margin-left: 60rpx;
		color: #4ebd71;
	}

	.bt1 text {
		font-size: 12px;
	}

	.bt1 .current {
		color: #5ac775;
	}

	.result {
		width: 40%;
		position: fixed;
		bottom: 20px;
		left: 50%;
		transform: translateX(-50%);
		background-color: #4ebd71;
		color: #fff;
		border-radius: 20px;
		z-index: 999;
	}
</style>
