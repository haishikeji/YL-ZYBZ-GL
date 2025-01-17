<template>
<div>
  <el-dialog
    :title="title"
    :close-on-click-modal="false"
     v-dialogDrag
    :visible.sync="visible">
    <el-form :model="inputForm" size="small" ref="inputForm" v-loading="loading" :class="method==='view'?'readonly':''"  :disabled="method==='view'"
             label-width="120px">
      <el-row  :gutter="15">
        <el-col :span="12">
            <el-form-item label="药名" prop="name"
                :rules="[
                  {required: true, message:'药名不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.name" placeholder="请填写药名"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="类别" prop="type.id"
                :rules="[
                  {required: true, message:'类别不能为空', trigger:'blur'}
                 ]">
          <SelectTree
                      ref="type"
                      :props="{
                          value: 'id',             // ID字段名
                          label: 'name',         // 显示名称
                          children: 'children'    // 子级字段名
                        }"

                      url="/zy/medicineclass/medicineClass/treeData"
                      :value="inputForm.type.id"
                      :clearable="true"
                      :accordion="true"
                      @getValue="(value) => {inputForm.type.id=value}"/>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="性味" prop="flavour.id"
                :rules="[
                  {required: true, message:'性味不能为空', trigger:'blur'}
                 ]">
          <GridSelect
            title="选择性味"
            labelName = 'flavour'
            labelValue = 'id'
            :value = "inputForm.flavour.id"
            :limit="5"
            @getValue='(value) => {inputForm.flavour.id=value}'
            :columns="[
            {
              prop: 'flavour',
              label: '性味'
            }
            ]"
            :searchs="[
            {
              prop: 'flavour',
              label: '性味'
            }
            ]"
            dataListUrl="/zy/flavour/flavour/list"
            queryEntityUrl="/zy/flavour/flavour/queryById">
          </GridSelect>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="经脉" prop="meridians.id"
                :rules="[
                  {required: true, message:'经脉不能为空', trigger:'blur'}
                 ]">
          <GridSelect
            title="选择经脉"
            labelName = 'meridians'
            labelValue = 'id'
            :value = "inputForm.meridians.id"
            :limit="5"
            @getValue='(value) => {inputForm.meridians.id=value}'
            :columns="[
            {
              prop: 'meridians',
              label: '经脉'
            }
            ]"
            :searchs="[
            {
              prop: 'meridians',
              label: '经脉'
            }
            ]"
            dataListUrl="/zy/meridians/meridians/list"
            queryEntityUrl="/zy/meridians/meridians/queryById">
          </GridSelect>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="功效" prop="efficacy"
                :rules="[
                  {required: true, message:'功效不能为空', trigger:'blur'}
                 ]">
              <el-input v-model="inputForm.efficacy" placeholder="请填写功效"     ></el-input>
           </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="临床应用" prop="clinical.id"
                :rules="[
                  {required: true, message:'临床应用不能为空', trigger:'blur'}
                 ]">
          <GridSelect
            title="选择临床应用"
            labelName = 'clinical'
            labelValue = 'id'
            :value = "inputForm.clinical.id"
            :limit="5"
            @getValue='(value) => {inputForm.clinical.id=value}'
            :columns="[
            {
              prop: 'clinical',
              label: '临床应用'
            }
            ]"
            :searchs="[
            {
              prop: 'clinical',
              label: '临床应用'
            }
            ]"
            dataListUrl="/zy/clinical/clinical/list"
            queryEntityUrl="/zy/clinical/clinical/queryById">
          </GridSelect>
           </el-form-item>
        </el-col>
        </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button size="small" @click="visible = false">关闭</el-button>
      <el-button size="small" type="primary" v-if="method != 'view'" @click="doSubmit()" v-noMoreClick>确定</el-button>
    </span>
  </el-dialog>
</div>
</template>

<script>
  import SelectTree from '@/components/treeSelect/treeSelect.vue'
  import GridSelect from '@/components/gridSelect'
  import ChineseMedicineService from '@/api/zy/chinesemedicine/ChineseMedicineService'
  export default {
    data () {
      return {
        title: '',
        method: '',
        visible: false,
        loading: false,
        inputForm: {
          id: '',
          name: '',
          type: {
            id: ''
          },
          flavour: {
            id: ''
          },
          meridians: {
            id: ''
          },
          efficacy: '',
          clinical: {
            id: ''
          }
        }
      }
    },
    components: {
      SelectTree,
      GridSelect
    },
    chineseMedicineService: null,
    created () {
      this.chineseMedicineService = new ChineseMedicineService()
    },
    methods: {
      init (method, id) {
        this.method = method
        this.inputForm.id = id
        if (method === 'add') {
          this.title = `新建药材管理`
        } else if (method === 'edit') {
          this.title = '修改药材管理'
        } else if (method === 'view') {
          this.title = '查看药材管理'
        }
        this.visible = true
        this.loading = false
        this.$nextTick(() => {
          this.$refs.inputForm.resetFields()
          if (method === 'edit' || method === 'view') { // 修改或者查看
            this.loading = true
            this.chineseMedicineService.queryById(this.inputForm.id).then(({data}) => {
              this.inputForm = this.recover(this.inputForm, data)
              this.loading = false
            })
          }
        })
      },
      // 表单提交
      doSubmit () {
        this.$refs['inputForm'].validate((valid) => {
          if (valid) {
            this.loading = true
            this.chineseMedicineService.save(this.inputForm).then(({data}) => {
              this.visible = false
              this.$message.success(data)
              this.$emit('refreshDataList')
              this.loading = false
            }).catch(() => {
              this.loading = false
            })
          }
        })
      }
    }
  }
</script>

  
