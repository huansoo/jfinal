-- ----------------------------
-- Records of jfsnpm_form_d
-- ----------------------------
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'0b01a16fa5404475926b380b729bb05d', N'concert', N'complete', N'40', N'dousersname', N'协作人', N'否', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'0eb020896eb44a02bcba0cb9a8789743', N'concert', N'get', N'20', N'concert_need_date', N'期望日期', N'否', N'否', N'否', N'否', N'否', null, N'date', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'1602544f20e94e628f1220c1b350b1bb', N'concert', N'close', N'30', N'dousers', N'dousers', N'否', N'否', N'否', N'否', N'否', null, N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'1e33ef5b6b5844ba8969be57cd817720', N'concert', N'complete', N'20', N'concert_need_date', N'期望日期', N'否', N'否', N'否', N'否', N'否', null, N'date', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'225e71177e6048dabf43b3ef15cb3ad2', N'leave', N'approveDept', N'30', N'approveDept', N'主管意见', N'是', N'是', N'是', N'是', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'22bc9e573e7b4084a806a557d5a55c81', N'concert', N'complete', N'41', N'douser', N'接收人', N'否', N'否', N'否', N'否', N'否', N'', N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'278750c8046443b696ec56295d111207', N'concert', N'send', N'30', N'dousers', N'dousers', N'否', N'是', N'是', N'是', N'是', null, N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'29ffec66d0844319abea9eefe8dd1fa1', N'concert', N'close', N'41', N'douser', N'接收人', N'否', N'否', N'否', N'否', N'否', N'', N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'2a29d6785bbb4f3eb1e3d2d17ea14379', N'concert', N'send', N'40', N'dousersname', N'协作人', N'是', N'是', N'是', N'是', N'是', null, N'findgrid', N'include:''dousers:id,dousersname:userName'',pk:''id'',multiple:true,append:true,gridOptions:{dataUrl:''/Lookup/user'', columns:[{name:''id'', label:''ID''},{name:''userNo'', label:''工号''},{name:''userName'', label:''姓名''}]}')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'2b53841b278a4d00a5c1829c2662299d', N'concert', N'close', N'10', N'concert_title', N'协作标题', N'否', N'否', N'否', N'否', N'否', null, N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'3493258c55ef45638687e43f7f03676d', N'concert', N'get', N'50', N'concert_send', N'协作内容', N'是', N'否', N'否', N'否', N'否', null, N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'36c4aad953264ec7ab7ad41c16f266de', N'concert', N'get', N'41', N'douser', N'接收人', N'否', N'是', N'否', N'否', N'是', N'__userId', N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'387f3324b9154636a3cf2742ed0c7d5c', N'concert', N'close', N'50', N'concert_send', N'协作内容', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'42669e1c512a4a3a8d3f0b6ef5697a7f', N'leave', N'approveDept', N'10', N'leave_title', N'请假说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'44c9601abbe24b9da5f249ad2fb8f135', N'leave', N'approveBoss', N'20', N'day', N'请假天数', N'是', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'4a96f271a367410f892872b58cc897e7', N'concert', N'get', N'60', N'concert_get', N'接收说明', N'是', N'是', N'是', N'是', N'否', null, N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'531b77d7d58e4b8a9c636e7e3e324c96', N'concert', N'complete', N'30', N'dousers', N'dousers', N'否', N'否', N'否', N'否', N'否', null, N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'58344921797b44af8f2933128cb8ff2d', N'concert', N'complete', N'42', N'dousername', N'接收人', N'否', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'6469bdf1424b4a6bb687a9cccedfb33a', N'concert', N'get', N'30', N'dousers', N'dousers', N'否', N'否', N'否', N'否', N'否', null, N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'64882a763cb04c0985354a5f46926f6d', N'concert', N'get', N'40', N'dousersname', N'协作人', N'否', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'64e2fe150b99468f952ab2b6655f4cb7', N'concert', N'readonly', N'70', N'concert_complete', N'完成说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'64fede5d483e4595b05755f3b9895202', N'concert', N'send', N'50', N'concert_send', N'协作内容', N'是', N'是', N'是', N'是', N'否', null, N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'67ab1fff54794c09b2957d690f5faeff', N'leave', N'approveBoss', N'30', N'approveDept', N'主管意见', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'69a9f65c8b0c4f57930fa37fdb73e42c', N'concert', N'close', N'70', N'concert_complete', N'完成说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'6b5a5d85138f4342a16cad4d88f4ebca', N'concert', N'complete', N'60', N'concert_get', N'接收说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'6e3c902be0154d6294e44b6ac22cee66', N'concert', N'get', N'42', N'dousername', N'接收人', N'否', N'是', N'否', N'否', N'是', N'__userName', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'73e37e9f6efa4bc3accbbc245ba2395b', N'concert', N'readonly', N'60', N'concert_get', N'接收说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'794ca3ca178a402cb22ebb33b2b249fc', N'concert', N'close', N'60', N'concert_get', N'接收说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'7dcf7294f85b4f0cbeff9552f961679e', N'leave', N'approveBoss', N'10', N'leave_title', N'请假说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'83435db5dbde4f519f1a10551cf2cb8f', N'concert', N'readonly', N'50', N'concert_send', N'协作内容', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'912cbb9c6f784b1b890320ea4b6cd971', N'concert', N'close', N'20', N'concert_need_date', N'期望日期', N'否', N'否', N'否', N'否', N'否', null, N'date', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'9cf06f66219e41a3ab14ca92186a1a89', N'concert', N'close', N'42', N'dousername', N'接收人', N'否', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'a0719346d24c4079958fd848780e802c', N'concert', N'readonly', N'40', N'dousersname', N'协作人', N'否', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'a0d40b12664c47b9ba85229710e2e34d', N'concert', N'readonly', N'80', N'concert_close', N'结案说明', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'a117032048d44e7ba81b99ccf11d87aa', N'concert', N'send', N'20', N'concert_need_date', N'期望日期', N'否', N'是', N'是', N'是', N'是', N'__today', N'date', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'a3c896c9d0394b96a45a35854a4fad76', N'concert', N'complete', N'70', N'concert_complete', N'完成说明', N'是', N'是', N'是', N'是', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'a56479ac63b54c549553a48e6173c0ee', N'concert', N'readonly', N'30', N'dousers', N'dousers', N'否', N'否', N'否', N'否', N'否', null, N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'a67069caf1fb46a79531e4bf398326ab', N'concert', N'send', N'10', N'concert_title', N'协作标题', N'否', N'是', N'是', N'是', N'否', null, N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'af0f170c21af45659e5330e17ddb85aa', N'concert', N'get', N'10', N'concert_title', N'协作标题', N'否', N'否', N'否', N'否', N'否', null, N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'b0c3bccd954e438da99d505edb745896', N'concert', N'readonly', N'10', N'concert_title', N'协作标题', N'否', N'否', N'否', N'否', N'否', null, N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'b19faf2661ed463581153c2a1ae64bf0', N'concert', N'readonly', N'20', N'concert_need_date', N'期望日期', N'否', N'否', N'否', N'否', N'否', null, N'date', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'b6107ef3aabe46189f2810b3643609dd', N'concert', N'readonly', N'41', N'douser', N'接收人', N'否', N'否', N'否', N'否', N'否', N'', N'hidden', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'c13b9b08ce734345a1447ed99f3b6a4f', N'concert', N'complete', N'50', N'concert_send', N'协作内容', N'是', N'否', N'否', N'否', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'c6f122389c5b4e91b4ec7abed922ac18', N'leave', N'approveBoss', N'40', N'approveBoss', N'经理意见', N'是', N'是', N'是', N'是', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'cfa2e4ca641f479bbd942369d19bb96f', N'leave', N'approveDept', N'20', N'day', N'请假天数', N'是', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'd17e7f81836e470b986582d35f4d3f1a', N'concert', N'complete', N'10', N'concert_title', N'协作标题', N'否', N'否', N'否', N'否', N'否', null, N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'd3fd1ca9d86a4edf99d6f0f4de8474ab', N'concert', N'close', N'40', N'dousersname', N'协作人', N'否', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'd86cd8d1a216431e81902c228fc4df26', N'concert', N'close', N'80', N'concert_close', N'结案说明', N'是', N'是', N'是', N'是', N'否', N'', N'textarea', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'e0f47ea7237e42c0bdc1463be42e5bab', N'leave', N'apply', N'20', N'day', N'请假天数', N'是', N'是', N'是', N'是', N'是', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'e3e5f7f7346e4d30b4ae2707d84c556d', N'concert', N'readonly', N'42', N'dousername', N'接收人', N'否', N'否', N'否', N'否', N'否', N'', N'text', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_d] ([id], [processName], [formName], [sortNo], [formColumn], [formLabel], [rowYn], [updateYN], [editYN], [requiredYN], [argsYN], [defaultValue], [type], [otherArgs]) VALUES (N'ec44e2c721a2407ba77546bc2c18a886', N'leave', N'apply', N'10', N'leave_title', N'请假说明', N'是', N'是', N'是', N'是', N'否', N'', N'textarea', N'')
GO
GO
-- ----------------------------
-- Records of jfsnpm_form_g
-- ----------------------------
INSERT INTO [dbo].[jfsnpm_form_g] ([id], [processName], [formName], [sortNo], [formCname], [formClabel], [formCwidth], [formCalign], [formCtype], [formCadd], [formCedit], [formCattrs], [formCrule], [formCitems], [formCrender], [formCpattern], [formCcalc], [formCcalcTit], [formCcalcDecimal], [formChide]) VALUES (N'e3edcd1c5e054452a7678338c76d88df', N'concert', N'close', N'20', N'itemdesc', N'物品描述', N'200', N'left', N'textarea', N'true', N'false', N'', N'', N'', N'', N'', N'', N'', N'', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_g] ([id], [processName], [formName], [sortNo], [formCname], [formClabel], [formCwidth], [formCalign], [formCtype], [formCadd], [formCedit], [formCattrs], [formCrule], [formCitems], [formCrender], [formCpattern], [formCcalc], [formCcalcTit], [formCcalcDecimal], [formChide]) VALUES (N'f16c49b149f2495f98699e95fa34194d', N'concert', N'close', N'10', N'itemname', N'物品名称', N'200', N'left', N'string', N'true', N'true', N'', N'', N'', N'', N'', N'', N'', N'', N'')
GO
GO

-- ----------------------------
-- Records of jfsnpm_form_h
-- ----------------------------
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'2439f965452e4fc5b378aaff79b2f621', N'leave', N'approveDept', N'apply', N'请假单', N'', N'approveDept', N'同意', N'退回', N'转发', N'', N'', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'29484d31e85c4d96aac0f034dccf63a1', N'concert', N'close', N'rect4', N'协作单', N'', N'concert_close', N'结案', N'退回', N'转发', N'form_concert_detail', N'parentid', N'add,edit,del')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'33f72ad29c444bc3818a599160f209b2', N'concert', N'complete', N'rect3', N'协作单', N'', N'concert_complete', N'完成', N'退回', N'转发', null, null, null)
GO
GO
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'60829713581b4b1bb6104fb7195235ef', N'leave', N'approveBoss', N'apply', N'请假单', N'', N'approveBoss', N'同意', N'退回', N'转发', N'', N'', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'7c7983670d4c41c8a6fefe5102b265ed', N'leave', N'apply', N'', N'请假单', N'leave_title', N'leave_title', N'送签', N'', N'', N'', N'', N'')
GO
GO
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'b67cde99788a4b02bc90d6515b273947', N'concert', N'get', N'rect3', N'协作单', N'', N'concert_get', N'接收', N'退回', N'转发', null, null, null)
GO
GO
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'bb8325ed318f40578c92c11dca5c912d', N'concert', N'send', N'', N'协作单', N'concert_title', N'concert_send', N'完成', N'', N'', null, null, null)
GO
GO
INSERT INTO [dbo].[jfsnpm_form_h] ([id], [processName], [formName], [formRejTo], [formDisplayName], [formUpdateTitle], [formUpdateDesc], [formOperaType1], [formOperaType2], [formOperaType3], [formGridDbName], [formGridDbKey], [formGridAuth]) VALUES (N'd838009e080f4db7a721f59d149ed7a9', N'concert', N'readonly', N'', N'协作单', N'', N'', N'同意', N'', N'', null, null, null)
GO
GO


-- ----------------------------
-- Records of jfsnpm_menu
-- ----------------------------
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_nbase', N'_ssystem', N'System', N'基础设置', N'_000000', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_ndemolanguage', N'_sdemo', N'Demo/language', N'测试不同语言显示', N'_001002', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_ndemomenu', N'_sdemo', N'Demo/test', N'测试菜单', N'_001000', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_ndemomenudisable', N'_sdemo', N'Demo999', N'测试菜单禁用', N'_001003', N'禁用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_ndemotrans', N'_sdemo', N'Demo/testtx', N'测试事务的传递', N'_001001', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_nfile', N'_nbase', N'System/filelist', N'公共文件', N'_000000005', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_nfile2', N'_nbase', N'File/list', N'磁盘文件管理', N'_000000006', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_nflow', N'_nbase', N'System/flowprocess', N'流程管理', N'_000000003', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_nflowstruct', N'_nbase', N'System/flowstruct', N'流程表单', N'_000000004', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_nmenu', N'_nbase', N'System/menu', N'菜单维护', N'_000000000', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_norg', N'_nbase', N'System/org', N'组织维护', N'_000000001', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_nrole', N'_nbase', N'System/role', N'角色维护', N'_000000002', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_root', null, N'', N'root', N'_', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_sdemo', N'_root', N'Demo', N'测试DEMO', N'_001', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'_ssystem', N'_root', N'System', N'系统管理', N'_000', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'3138c846040248a9b434da747ed7100f', N'_sdemo', N'Demo/datagrid_remote', N'远程数据操作', N'_001004', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_menu] ([id], [pId], [url], [text], [sortNo], [status]) VALUES (N'c088d4efed3441dd9b10ce9ce0da8057', N'_sdemo', N'Demo/testhtml', N'测试动态HTML', N'_001005', N'启用')
GO
GO

-- ----------------------------
-- Records of jfsnpm_org
-- ----------------------------
INSERT INTO [dbo].[jfsnpm_org] ([id], [pId], [text], [sortNo], [status]) VALUES (N'_root', null, N'root', N'_', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_org] ([id], [pId], [text], [sortNo], [status]) VALUES (N'gm', N'_root', N'总经理', N'_000', N'启用')
GO
GO
INSERT INTO [dbo].[jfsnpm_org] ([id], [pId], [text], [sortNo], [status]) VALUES (N'it', N'gm', N'IT', N'_000000', N'启用')
GO
GO

-- ----------------------------
-- Records of jfsnpm_role
-- ----------------------------
INSERT INTO [dbo].[jfsnpm_role] ([id], [name]) VALUES (N'admin', N'管理员')
GO
GO
INSERT INTO [dbo].[jfsnpm_role] ([id], [name]) VALUES (N'test', N'测试用户0')
GO
GO


-- ----------------------------
-- Records of jfsnpmr_process_role
-- ----------------------------
INSERT INTO [dbo].[jfsnpmr_process_role] ([processId], [roleId]) VALUES (N'concert', N'admin')
GO
GO
INSERT INTO [dbo].[jfsnpmr_process_role] ([processId], [roleId]) VALUES (N'leave', N'admin')
GO
GO

-- ----------------------------
-- Records of wf_process
-- ----------------------------
INSERT INTO [dbo].[wf_process] ([id], [name], [display_Name], [type], [instance_Url], [state], [content], [version], [create_Time], [creator]) VALUES (N'concert', N'concert', N'协作任务', null, N'', N'1', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822207374616E64616C6F6E653D226E6F223F3E0A3C70726F63657373206E616D653D22636F6E636572742220646973706C61794E616D653D22E58D8FE4BD9CE4BBBBE58AA122203E0A3C7374617274206C61796F75743D223137362C31392C35302C353022206E616D653D22737461727422203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743322206E616D653D22706174683722202F3E0A3C2F73746172743E0A3C656E64206C61796F75743D223430302C31372C35302C353022206E616D653D22656E6422203E0A3C2F656E643E0A3C7461736B206C61796F75743D223135312C3131382C3130302C353022206E616D653D2272656374332220646973706C61794E616D653D22E58D8FE4BD9CE58F91E8B5B72220666F726D3D2273656E64222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743422206E616D653D22706174683822202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223135302C3231372C3130302C353022206E616D653D2272656374342220646973706C61794E616D653D22E58D8FE4BD9CE68EA5E694B62220666F726D3D22676574222061737369676E65653D22646F757365727322207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743522206E616D653D22706174683922202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223337372C3231362C3130302C353022206E616D653D2272656374352220646973706C61794E616D653D22E58D8FE4BD9CE58A9EE790862220666F726D3D22636F6D706C657465222061737369676E65653D22646F7573657222207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743622206E616D653D2270617468313022202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223337362C3131342C3130302C353022206E616D653D2272656374362220646973706C61794E616D653D22E58D8FE4BD9CE7BB93E6A1882220666F726D3D22636C6F7365222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22656E6422206E616D653D2270617468313122202F3E0A3C2F7461736B3E0A3C2F70726F636573733E, N'0', N'2015-10-28 15:41:26', null)
GO
GO
INSERT INTO [dbo].[wf_process] ([id], [name], [display_Name], [type], [instance_Url], [state], [content], [version], [create_Time], [creator]) VALUES (N'leave', N'leave', N'请假流程测试', null, N'', N'1', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822207374616E64616C6F6E653D226E6F223F3E0A3C70726F63657373206E616D653D226C656176652220646973706C61794E616D653D22E8AFB7E58187E6B581E7A88BE6B58BE8AF9522203E0A3C7374617274206C61796F75743D2232342C3132342C35302C353022206E616D653D227374617274312220646973706C61794E616D653D2273746172743122203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D226170706C7922206E616D653D227472616E736974696F6E3122202F3E0A3C2F73746172743E0A3C656E64206C61796F75743D223537302C3132342C35302C353022206E616D653D22656E64312220646973706C61794E616D653D22656E643122203E0A3C2F656E643E0A3C7461736B206C61796F75743D223131372C3132322C3130302C353022206E616D653D226170706C792220646973706C61794E616D653D22E8AFB7E58187E794B3E8AFB72220666F726D3D226170706C79222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22617070726F76654465707422206E616D653D227472616E736974696F6E3222202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223237322C3132322C3130302C353022206E616D653D22617070726F7665446570742220646973706C61794E616D653D22E983A8E997A8E7BB8FE79086E5AEA1E689B92220666F726D3D22617070726F766544657074222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D226465636973696F6E3122206E616D653D227472616E736974696F6E3322202F3E0A3C2F7461736B3E0A3C6465636973696F6E206C61796F75743D223432362C3132342C35302C353022206E616D653D226465636973696F6E312220657870723D22247B646179202667743B2032203F20277472616E736974696F6E3527203A20277472616E736974696F6E34277D2220646973706C61794E616D653D226465636973696F6E3122203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22656E643122206E616D653D227472616E736974696F6E342220646973706C61794E616D653D22266C743B3D32E5A4A922202F3E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22617070726F7665426F737322206E616D653D227472616E736974696F6E352220646973706C61794E616D653D222667743B32E5A4A922202F3E0A3C2F6465636973696F6E3E0A3C7461736B206C61796F75743D223430342C3233312C3130302C353022206E616D653D22617070726F7665426F73732220646973706C61794E616D653D22E680BBE7BB8FE79086E5AEA1E689B92220666F726D3D22617070726F7665426F7373222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22656E643122206E616D653D227472616E736974696F6E3622202F3E0A3C2F7461736B3E0A3C2F70726F636573733E, N'0', N'2015-12-29 11:17:25', null)
GO
GO