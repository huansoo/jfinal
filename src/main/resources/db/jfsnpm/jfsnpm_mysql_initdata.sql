-- ----------------------------
-- Records of jfsnpm_form_d
-- ----------------------------
INSERT INTO `jfsnpm_form_d` VALUES ('0b01a16fa5404475926b380b729bb05d', 'concert', 'complete', '40', 'dousersname', '协作人', '否', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('0eb020896eb44a02bcba0cb9a8789743', 'concert', 'get', '20', 'concert_need_date', '期望日期', '否', '否', '否', '否', '否', null, 'date', '');
INSERT INTO `jfsnpm_form_d` VALUES ('1602544f20e94e628f1220c1b350b1bb', 'concert', 'close', '30', 'dousers', 'dousers', '否', '否', '否', '否', '否', null, 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('1e33ef5b6b5844ba8969be57cd817720', 'concert', 'complete', '20', 'concert_need_date', '期望日期', '否', '否', '否', '否', '否', null, 'date', '');
INSERT INTO `jfsnpm_form_d` VALUES ('225e71177e6048dabf43b3ef15cb3ad2', 'leave', 'approveDept', '30', 'approveDept', '主管意见', '是', '是', '是', '是', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('22bc9e573e7b4084a806a557d5a55c81', 'concert', 'complete', '41', 'douser', '接收人', '否', '否', '否', '否', '否', '', 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('278750c8046443b696ec56295d111207', 'concert', 'send', '30', 'dousers', 'dousers', '否', '是', '是', '是', '是', null, 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('29ffec66d0844319abea9eefe8dd1fa1', 'concert', 'close', '41', 'douser', '接收人', '否', '否', '否', '否', '否', '', 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('2a29d6785bbb4f3eb1e3d2d17ea14379', 'concert', 'send', '40', 'dousersname', '协作人', '是', '是', '是', '是', '是', null, 'findgrid', 'include:''dousers:id,dousersname:userName'',pk:''id'',multiple:true,append:true,gridOptions:{dataUrl:''/Lookup/user'', columns:[{name:''id'', label:''ID''},{name:''userNo'', label:''工号''},{name:''userName'', label:''姓名''}]}');
INSERT INTO `jfsnpm_form_d` VALUES ('2b53841b278a4d00a5c1829c2662299d', 'concert', 'close', '10', 'concert_title', '协作标题', '否', '否', '否', '否', '否', null, 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('3493258c55ef45638687e43f7f03676d', 'concert', 'get', '50', 'concert_send', '协作内容', '是', '否', '否', '否', '否', null, 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('36c4aad953264ec7ab7ad41c16f266de', 'concert', 'get', '41', 'douser', '接收人', '否', '是', '否', '否', '是', '__userId', 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('387f3324b9154636a3cf2742ed0c7d5c', 'concert', 'close', '50', 'concert_send', '协作内容', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('42669e1c512a4a3a8d3f0b6ef5697a7f', 'leave', 'approveDept', '10', 'leave_title', '请假说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('44c9601abbe24b9da5f249ad2fb8f135', 'leave', 'approveBoss', '20', 'day', '请假天数', '是', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('4a96f271a367410f892872b58cc897e7', 'concert', 'get', '60', 'concert_get', '接收说明', '是', '是', '是', '是', '否', null, 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('531b77d7d58e4b8a9c636e7e3e324c96', 'concert', 'complete', '30', 'dousers', 'dousers', '否', '否', '否', '否', '否', null, 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('58344921797b44af8f2933128cb8ff2d', 'concert', 'complete', '42', 'dousername', '接收人', '否', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('6469bdf1424b4a6bb687a9cccedfb33a', 'concert', 'get', '30', 'dousers', 'dousers', '否', '否', '否', '否', '否', null, 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('64882a763cb04c0985354a5f46926f6d', 'concert', 'get', '40', 'dousersname', '协作人', '否', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('64e2fe150b99468f952ab2b6655f4cb7', 'concert', 'readonly', '70', 'concert_complete', '完成说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('64fede5d483e4595b05755f3b9895202', 'concert', 'send', '50', 'concert_send', '协作内容', '是', '是', '是', '是', '否', null, 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('67ab1fff54794c09b2957d690f5faeff', 'leave', 'approveBoss', '30', 'approveDept', '主管意见', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('69a9f65c8b0c4f57930fa37fdb73e42c', 'concert', 'close', '70', 'concert_complete', '完成说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('6b5a5d85138f4342a16cad4d88f4ebca', 'concert', 'complete', '60', 'concert_get', '接收说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('6e3c902be0154d6294e44b6ac22cee66', 'concert', 'get', '42', 'dousername', '接收人', '否', '是', '否', '否', '是', '__userName', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('73e37e9f6efa4bc3accbbc245ba2395b', 'concert', 'readonly', '60', 'concert_get', '接收说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('794ca3ca178a402cb22ebb33b2b249fc', 'concert', 'close', '60', 'concert_get', '接收说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('7dcf7294f85b4f0cbeff9552f961679e', 'leave', 'approveBoss', '10', 'leave_title', '请假说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('83435db5dbde4f519f1a10551cf2cb8f', 'concert', 'readonly', '50', 'concert_send', '协作内容', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('912cbb9c6f784b1b890320ea4b6cd971', 'concert', 'close', '20', 'concert_need_date', '期望日期', '否', '否', '否', '否', '否', null, 'date', '');
INSERT INTO `jfsnpm_form_d` VALUES ('9cf06f66219e41a3ab14ca92186a1a89', 'concert', 'close', '42', 'dousername', '接收人', '否', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('a0719346d24c4079958fd848780e802c', 'concert', 'readonly', '40', 'dousersname', '协作人', '否', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('a0d40b12664c47b9ba85229710e2e34d', 'concert', 'readonly', '80', 'concert_close', '结案说明', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('a117032048d44e7ba81b99ccf11d87aa', 'concert', 'send', '20', 'concert_need_date', '期望日期', '否', '是', '是', '是', '是', '__today', 'date', '');
INSERT INTO `jfsnpm_form_d` VALUES ('a3c896c9d0394b96a45a35854a4fad76', 'concert', 'complete', '70', 'concert_complete', '完成说明', '是', '是', '是', '是', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('a56479ac63b54c549553a48e6173c0ee', 'concert', 'readonly', '30', 'dousers', 'dousers', '否', '否', '否', '否', '否', null, 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('a67069caf1fb46a79531e4bf398326ab', 'concert', 'send', '10', 'concert_title', '协作标题', '否', '是', '是', '是', '否', null, 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('af0f170c21af45659e5330e17ddb85aa', 'concert', 'get', '10', 'concert_title', '协作标题', '否', '否', '否', '否', '否', null, 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('b0c3bccd954e438da99d505edb745896', 'concert', 'readonly', '10', 'concert_title', '协作标题', '否', '否', '否', '否', '否', null, 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('b19faf2661ed463581153c2a1ae64bf0', 'concert', 'readonly', '20', 'concert_need_date', '期望日期', '否', '否', '否', '否', '否', null, 'date', '');
INSERT INTO `jfsnpm_form_d` VALUES ('b6107ef3aabe46189f2810b3643609dd', 'concert', 'readonly', '41', 'douser', '接收人', '否', '否', '否', '否', '否', '', 'hidden', '');
INSERT INTO `jfsnpm_form_d` VALUES ('c13b9b08ce734345a1447ed99f3b6a4f', 'concert', 'complete', '50', 'concert_send', '协作内容', '是', '否', '否', '否', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('c6f122389c5b4e91b4ec7abed922ac18', 'leave', 'approveBoss', '40', 'approveBoss', '经理意见', '是', '是', '是', '是', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('cfa2e4ca641f479bbd942369d19bb96f', 'leave', 'approveDept', '20', 'day', '请假天数', '是', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('d17e7f81836e470b986582d35f4d3f1a', 'concert', 'complete', '10', 'concert_title', '协作标题', '否', '否', '否', '否', '否', null, 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('d3fd1ca9d86a4edf99d6f0f4de8474ab', 'concert', 'close', '40', 'dousersname', '协作人', '否', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('d86cd8d1a216431e81902c228fc4df26', 'concert', 'close', '80', 'concert_close', '结案说明', '是', '是', '是', '是', '否', '', 'textarea', '');
INSERT INTO `jfsnpm_form_d` VALUES ('e0f47ea7237e42c0bdc1463be42e5bab', 'leave', 'apply', '20', 'day', '请假天数', '是', '是', '是', '是', '是', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('e3e5f7f7346e4d30b4ae2707d84c556d', 'concert', 'readonly', '42', 'dousername', '接收人', '否', '否', '否', '否', '否', '', 'text', '');
INSERT INTO `jfsnpm_form_d` VALUES ('ec44e2c721a2407ba77546bc2c18a886', 'leave', 'apply', '10', 'leave_title', '请假说明', '是', '是', '是', '是', '否', '', 'textarea', '');

-- ----------------------------
-- Records of jfsnpm_form_g
-- ----------------------------
INSERT INTO `jfsnpm_form_g` VALUES ('e3edcd1c5e054452a7678338c76d88df', 'concert', 'close', '20', 'itemdesc', '物品描述', '200', 'left', 'textarea', 'true', 'false', '', '', '', '', '', '', '', '', '');
INSERT INTO `jfsnpm_form_g` VALUES ('f16c49b149f2495f98699e95fa34194d', 'concert', 'close', '10', 'itemname', '物品名称', '200', 'left', 'string', 'true', 'true', '', '', '', '', '', '', '', '', '');


-- ----------------------------
-- Records of jfsnpm_form_h
-- ----------------------------
INSERT INTO `jfsnpm_form_h` VALUES ('2439f965452e4fc5b378aaff79b2f621', 'leave', 'approveDept', 'apply', '请假单', '', 'approveDept', '同意', '退回', '转发', '', '', '', '');
INSERT INTO `jfsnpm_form_h` VALUES ('29484d31e85c4d96aac0f034dccf63a1', 'concert', 'close', 'rect4', '协作单', '', 'concert_close', '结案', '退回', '转发', 'form_concert_detail', 'parentid', 'add,edit,del', '');
INSERT INTO `jfsnpm_form_h` VALUES ('33f72ad29c444bc3818a599160f209b2', 'concert', 'complete', 'rect3', '协作单', '', 'concert_complete', '完成', '退回', '转发', null, null, null, '');
INSERT INTO `jfsnpm_form_h` VALUES ('60829713581b4b1bb6104fb7195235ef', 'leave', 'approveBoss', 'apply', '请假单', '', 'approveBoss', '同意', '退回', '转发', '', '', '', '');
INSERT INTO `jfsnpm_form_h` VALUES ('7c7983670d4c41c8a6fefe5102b265ed', 'leave', 'apply', '', '请假单', 'leave_title', 'leave_title', '送签', '', '', '', '', '', '');
INSERT INTO `jfsnpm_form_h` VALUES ('b67cde99788a4b02bc90d6515b273947', 'concert', 'get', 'rect3', '协作单', '', 'concert_get', '接收', '退回', '转发', null, null, null, '');
INSERT INTO `jfsnpm_form_h` VALUES ('bb8325ed318f40578c92c11dca5c912d', 'concert', 'send', '', '协作单', 'concert_title', 'concert_send', '完成', '', '', null, null, null, '');
INSERT INTO `jfsnpm_form_h` VALUES ('d838009e080f4db7a721f59d149ed7a9', 'concert', 'readonly', '', '协作单', '', '', '同意', '', '', null, null, null, '');

-- ----------------------------
-- Records of jfsnpm_menu
-- ----------------------------
INSERT INTO `jfsnpm_menu` VALUES ('3138c846040248a9b434da747ed7100f', '_sdemo', 'Demo/datagrid_remote', '远程数据操作', '_001004', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('c088d4efed3441dd9b10ce9ce0da8057', '_sdemo', 'Demo/testhtml', '测试动态HTML', '_001005', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_nbase', '_ssystem', 'System', '基础设置', '_000000', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_ndemolanguage', '_sdemo', 'Demo/language', '测试不同语言显示', '_001002', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_ndemomenu', '_sdemo', 'Demo/test', '测试菜单', '_001000', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_ndemomenudisable', '_sdemo', 'Demo999', '测试菜单禁用', '_001003', '禁用');
INSERT INTO `jfsnpm_menu` VALUES ('_ndemotrans', '_sdemo', 'Demo/testtx', '测试事务的传递', '_001001', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_nfile', '_nbase', 'System/filelist', '公共文件', '_000000005', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_nfile2', '_nbase', 'File/list', '磁盘文件管理', '_000000006', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_nflow', '_nbase', 'System/flowprocess', '流程管理', '_000000003', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_nflowstruct', '_nbase', 'System/flowstruct', '流程表单', '_000000004', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_nmenu', '_nbase', 'System/menu', '菜单维护', '_000000000', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_norg', '_nbase', 'System/org', '组织维护', '_000000001', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_nrole', '_nbase', 'System/role', '角色维护', '_000000002', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_root', null, '', 'root', '_', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_sdemo', '_root', 'Demo', '测试DEMO', '_001', '启用');
INSERT INTO `jfsnpm_menu` VALUES ('_ssystem', '_root', 'System', '系统管理', '_000', '启用');


-- ----------------------------
-- Records of jfsnpm_org
-- ----------------------------
INSERT INTO `jfsnpm_org` VALUES ('gm', '_root', '总经理', '_000', '启用');
INSERT INTO `jfsnpm_org` VALUES ('it', 'gm', 'IT', '_000000', '启用');
INSERT INTO `jfsnpm_org` VALUES ('_root', null, 'root', '_', '启用');


-- ----------------------------
-- Records of jfsnpm_role
-- ----------------------------
INSERT INTO `jfsnpm_role` VALUES ('admin', '管理员');
INSERT INTO `jfsnpm_role` VALUES ('test', '测试用户0');


-- ----------------------------
-- Records of jfsnpmr_process_role
-- ----------------------------
INSERT INTO `jfsnpmr_process_role` VALUES ('concert', 'admin');
INSERT INTO `jfsnpmr_process_role` VALUES ('leave', 'admin');

-- ----------------------------
-- Records of wf_process
-- ----------------------------
INSERT INTO `wf_process` VALUES ('concert', 'concert', '协作任务', null, '', '1', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822207374616E64616C6F6E653D226E6F223F3E0A3C70726F63657373206E616D653D22636F6E636572742220646973706C61794E616D653D22E58D8FE4BD9CE4BBBBE58AA122203E0A3C7374617274206C61796F75743D223137362C31392C35302C353022206E616D653D22737461727422203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743322206E616D653D22706174683722202F3E0A3C2F73746172743E0A3C656E64206C61796F75743D223430302C31372C35302C353022206E616D653D22656E6422203E0A3C2F656E643E0A3C7461736B206C61796F75743D223135312C3131382C3130302C353022206E616D653D2272656374332220646973706C61794E616D653D22E58D8FE4BD9CE58F91E8B5B72220666F726D3D2273656E64222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743422206E616D653D22706174683822202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223135302C3231372C3130302C353022206E616D653D2272656374342220646973706C61794E616D653D22E58D8FE4BD9CE68EA5E694B62220666F726D3D22676574222061737369676E65653D22646F757365727322207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743522206E616D653D22706174683922202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223337372C3231362C3130302C353022206E616D653D2272656374352220646973706C61794E616D653D22E58D8FE4BD9CE58A9EE790862220666F726D3D22636F6D706C657465222061737369676E65653D22646F7573657222207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22726563743622206E616D653D2270617468313022202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223337362C3131342C3130302C353022206E616D653D2272656374362220646973706C61794E616D653D22E58D8FE4BD9CE7BB93E6A1882220666F726D3D22636C6F7365222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222065787069726554696D653D22636F6E636572745F6E6565645F6461746522203E0A3C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22656E6422206E616D653D2270617468313122202F3E0A3C2F7461736B3E0A3C2F70726F636573733E, '0', '2015-10-28 15:41:26', null);
INSERT INTO `wf_process` VALUES ('leave', 'leave', '请假流程测试', null, '', '1', 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822207374616E64616C6F6E653D226E6F223F3E0A3C70726F63657373206E616D653D226C656176652220646973706C61794E616D653D22E8AFB7E58187E6B581E7A88BE6B58BE8AF9522203E0A3C7374617274206C61796F75743D2232342C3132342C35302C353022206E616D653D227374617274312220646973706C61794E616D653D2273746172743122203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D226170706C7922206E616D653D227472616E736974696F6E3122202F3E0A3C2F73746172743E0A3C656E64206C61796F75743D223537302C3132342C35302C353022206E616D653D22656E64312220646973706C61794E616D653D22656E643122203E0A3C2F656E643E0A3C7461736B206C61796F75743D223131372C3132322C3130302C353022206E616D653D226170706C792220646973706C61794E616D653D22E8AFB7E58187E794B3E8AFB72220666F726D3D226170706C79222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22617070726F76654465707422206E616D653D227472616E736974696F6E3222202F3E0A3C2F7461736B3E0A3C7461736B206C61796F75743D223237322C3132322C3130302C353022206E616D653D22617070726F7665446570742220646973706C61794E616D653D22E983A8E997A8E7BB8FE79086E5AEA1E689B92220666F726D3D22617070726F766544657074222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D226465636973696F6E3122206E616D653D227472616E736974696F6E3322202F3E0A3C2F7461736B3E0A3C6465636973696F6E206C61796F75743D223432362C3132342C35302C353022206E616D653D226465636973696F6E312220657870723D22247B646179202667743B2032203F20277472616E736974696F6E3527203A20277472616E736974696F6E34277D2220646973706C61794E616D653D226465636973696F6E3122203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22656E643122206E616D653D227472616E736974696F6E342220646973706C61794E616D653D22266C743B3D32E5A4A922202F3E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22617070726F7665426F737322206E616D653D227472616E736974696F6E352220646973706C61794E616D653D222667743B32E5A4A922202F3E0A3C2F6465636973696F6E3E0A3C7461736B206C61796F75743D223430342C3233312C3130302C353022206E616D653D22617070726F7665426F73732220646973706C61794E616D653D22E680BBE7BB8FE79086E5AEA1E689B92220666F726D3D22617070726F7665426F7373222061737369676E65653D225F5F75736572496422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922203E0A3C7472616E736974696F6E206F66667365743D22302C302220746F3D22656E643122206E616D653D227472616E736974696F6E3622202F3E0A3C2F7461736B3E0A3C2F70726F636573733E, '0', '2015-12-29 11:17:25', null);
