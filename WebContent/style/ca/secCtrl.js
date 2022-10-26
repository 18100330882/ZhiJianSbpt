////////////////////////////////////////////////////
//定义相关接口参数
// 非对称算法
var ALGID_AUTO = 0;							// 使用证书时自动适配
var ALGID_RSA1024 = 1;						// RSA 1024位算法
var ALGID_RSA2048 = 2;						// RSA 2048位算法
var ALGID_ECC = 3;							// SM2 256位算法

// 摘要算法
var ALGID_HASH_SHA1 = 1;					//SHA-1算法
var ALGID_HASH_SHA256 = 2;  				//SHA-256算法
var ALGID_HASH_SHA512 = 3;  				//SHA-512算法
var ALGID_HASH_MD5 = 4;     				//MD5算法
var ALGID_HASH_MD4 = 5; 					//MD4算法
var ALGID_HASH_SM3 = 6;						//SM3算法

// 证书类型定义
var ENCRYPT_TYPE = 1;						//加密证书
var SIGN_TYPE = 2;							//签名证书

// 证书基本项
var X509_CERT_VERSION = 1;					// 证书版本
var X509_CERT_SERIAL = 2;					// 证书序列号
var X509_CERT_SIGNALG = 3;					// 证书签名算法标识
var X509_CERT_ISSUER_C = 4;					// 证书颁发者国家(C)
var X509_CERT_ISSUER_O = 5;					// 证书颁发者组织名(O)
var X509_CERT_ISSUER_OU = 6;				// 证书颁发者部门名(OU)
var X509_CERT_ISSUER_S = 7;					// 证书颁发者所在的省、自治区、直辖市(S)
var X509_CERT_ISSUER_CN = 8;				// 证书颁发者通用名称(CN)
var X509_CERT_ISSUER_L = 9;					// 证书颁发者所在的城市、地区(L)
var X509_CERT_ISSUER_E = 10;				// 证书颁发者Email
var X509_CERT_NOTBEFORE = 11;				// 证书有效期：起始日期
var X509_CERT_NOTAFTER = 12;				// 证书有效期：终止日期
var X509_CERT_SUBJECT_C = 13;				// 证书拥有者国家(C )
var X509_CERT_SUBJECT_O = 14;				// 证书拥有者组织名(O)
var X509_CERT_SUBJECT_OU = 15;				// 证书拥有者部门名(OU)
var X509_CERT_SUBJECT_S = 16;				// 证书拥有者所在的省、自治区、直辖市(S)
var X509_CERT_SUBJECT_CN = 17;				// 证书拥有者通用名称(CN)
var X509_CERT_SUBJECT_L = 18;				// 证书拥有者所在的城市、地区(L)
var X509_CERT_SUBJECT_E = 19;				// 证书拥有者Email
var X509_CERT_ISSUER_DN = 20;				// 证书颁发者DN
var X509_CERT_SUBJECT_DN = 21;				// 证书拥有者DN
var X509_CERT_DER_PUBKEY = 22;				// 证书公钥信息
var X509_CERT_EXT_CRLDISTRIBUTIONPO = 23;	// CRL发布点
////////////////////////////////////////////////////

// 管理员key类型配置
var ks_provider = ""; // 介质
var ks_alg = ALGID_AUTO;    // 非对称算法，根据证书内容适配
var ks_path = "C:\\CONT\\?";   // 如果为软算法，对应路径
var ks_hash_alg = ALGID_AUTO; // 自动适配算法，RSA时为SHA1, SM2时为SM3

if (IsIE()) {
    document.write("<object id=\"SecCtrl\" classid=\"clsid:17F8D3CF-857C-4D7C-9355-7A2398930B6A\" hidden=\"true\" width=\"0\" height=\"0\" codebase=\"downfile\\npSecCtrl.cab#version=1,2,0,0\">");
    document.write("</object>");
} else {
    document.write("<embed id=\"SecCtrl\" type=\"application/npsecctrl-plugin\"  hidden=\"true\" width=\"0\" height=\"0\"/>");
}

function IsIE() {
    //判断是否为IE内核浏览器
    var u = window.navigator.userAgent.toLocaleLowerCase();
    var msie = /(msie) ([\d.]+)/;
    var chrome = /(chrome)\/([\d.]+)/;
    var firefox = /(firefox)\/([\d.]+)/;
    var safari = /(safari)\/([\d.]+)/;
    var opera = /(opera)\/([\d.]+)/;
    var ie11 = /(trident)\/([\d.]+)/;
    b = u.match(msie) || u.match(chrome) || u.match(firefox) || u.match(safari) || u.match(opera) || u.match(ie11);
    if (b[1] == "msie" || b[1] == 'trident') {
        return true;
    } else {
        return false;
    }
}

/**
 * 枚举支持的provider
 * provider: select ID
 */
function EnumProvider(provider) {
    while (provider.length > 0) {
        provider.remove(0);
    }

    var elem3 = document.createElement("option");
    elem3.value = "XACA";
    elem3.text = "自动识别";
    provider.add(elem3);

    var elem1 = document.createElement("option");
    elem1.value = "soft_file";
    elem1.text = "文件证书";
    provider.add(elem1);

    var elem2 = document.createElement("option");
    elem2.value = "SKF&SKFAPI20509.dll";
    elem2.text = "海泰USB KEY";
    provider.add(elem2);

    var elem4 = document.createElement("option");
    elem4.value = "SKF&HNCA3000GM.dll";
    elem4.text = "飞天USB KEY";
    provider.add(elem4);

    if (provider.length > 0) {
        provider.selectedIndex = 0;
    }
}

/**
 * 初始化函数，设置相关初始值
 * p_provider: 算法提供者
 * p_alg: 非对称算法，1-RSA1024, 2-RSA2048, 3-ECC
 * p_path: 路径
 */
function init(p_provider, p_alg, p_path) {
    if (p_provider != null && p_provider != "") {
        ks_provider = p_provider;
    }
    if (p_alg != null && p_alg != "") {
        ks_alg = p_alg;
    }
    if (p_path != null && p_path != "") {
        ks_path = p_path;
    }
    SecCtrl.KS_SetProv(ks_provider, ks_alg, ks_path);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
}

/**
 * 获取版本号
 */
function GetVersion() {
    var result = SecCtrl.KS_GetVersion();
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    result = SecCtrl.KS_GetSealList();
    return result;
}

/**
 * 获取插KeyVid
 */
function GetDeviceVid() {
    var result = "";
    var lRet = SecCtrl.KS_SetProv(ks_provider, ks_alg, ks_path);
    if (lRet == 0)
        result = SecCtrl.KS_GetDeviceVid();
    else
        result = "SetProv error.";
    return result;
}

/**
 *    获取key的唯一码
 */
function GetGetDeviceSerialNumber() {
    var result = "";
    var lRet = SecCtrl.KS_SetProv(ks_provider, ks_alg, ks_path);
    if (lRet != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return result;
    }
    result = SecCtrl.KS_GetAllDeviceSerialNumber();
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}


/**
 * 设置过滤器，在算法提供者为soft_ie的时候适用
 * filter: 参数;
 例如:CERT_SN=1234&ISSUER_C=CN&ISSUER_CN=测试证书&ISSUER_O=测试单位&ISSUER_OU=测试证书&ISSUER_L=城市&ISSUER_S=省份&ISSUER_E=yangxunx@foxmail.com&SUBJECT_C=CN&SUBJECT_CN=测试证书&SUBJECT_O=测试单位&SUBJECT_OU=测试证书&SUBJECT_L=城市&SUBJECT_S=省份&SUBJECT_E=yangxunx@foxmail.com
 */
function SetFilter(filter) {
    var result = "";
    result = SecCtrl.KS_SetParam("filter", filter);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 获取BASE64编码证书
 * type: 1、加密证书，2、签名证书
 */
function GetCert(type) {
    var result = "";

    result = SecCtrl.KS_GetCert(type);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 获取证书信息
 * cert: Base64编码证书
 * item: 解析项。
 * 1、证书版本；2、证书序列号；3、证书签名算法标识；4、证书颁发者国家(C); 5、证书颁发者组织名(O);
 * 6、证书颁发者部门名(OU); 7、证书颁发者所在的省、自治区、直辖市(S); 8、证书颁发者通用名称(CN); 9、证书颁发者所在的城市、地区(L);
 * 10、证书颁发者Email; 11、证书有效期：起始日期; 12、证书有效期：终止日期; 13、证书拥有者国家(C ); 14、证书拥有者组织名(O);
 * 15、证书拥有者部门名(OU); 16、证书拥有者所在的省、自治区、直辖市(S); 17、证书拥有者通用名称(CN); 18、证书拥有者所在的城市、地区(L);
 * 19、证书拥有者Email; 20、证书颁发者DN; 21、证书拥有者DN; 22、证书公钥信息; 23、CRL发布点.
 */
function GetCertInfo(cert, item) {
    var result = "";
    result = SecCtrl.KS_GetCertInfo(cert, item);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 获取证书扩展信息
 * cert: Base64编码证书
 * oid: oid值
 */
function GetCertInfoByOid(cert, oid) {
    var result = "";

    result = SecCtrl.KS_GetCertInfoByOid(cert, oid);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 生成随机数
 * len: 随机数长度
 */
function GenRandom(len) {
    var result = "";

    result = SecCtrl.KS_GenRandom(len);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 数据摘要
 * indata: 输入数据
 * hashAlg:摘要算法。1-SHA1, 2-SHA256, 3-SHA512, 4-MD5, 5-MD4
 */
function HashData(indata, hashAlg) {
    var result = "";
    result = SecCtrl.KS_HashData(indata, hashAlg);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 文件摘要
 * filepath: 输入文件路径
 * hashAlg:摘要算法。1-SHA1, 2-SHA256, 3-SHA512, 4-MD5, 5-MD4
 */
function HashFile(filepath, hashAlg) {
    var result = "";
    result = SecCtrl.KS_HashFile(filepath, hashAlg);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 数据签名
 * indata：明文数据
 * hashAlg: 1-SHA1, 2-SHA256, 3-SHA512, 4-MD5, 5-MD4, 6-SM3
 * return：签名数据
 */
function SignData(indata, hashAlg) {
    var result = "";
    result = SecCtrl.KS_SignData(indata, hashAlg);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 数据验签
 * indata：明文数据
 * signdata：签名数据
 * cert: 证书
 * hashAlg: 1-SHA1, 2-SHA256, 3-SHA512, 4-MD5, 5-MD4, 6-SM3
 * return：成功=true，失败=false
 */
function VerifySignData(indata, signdata, cert, hashAlg) {
    var lRet = SecCtrl.KS_VerifySignData(indata, signdata, cert, hashAlg);
    return lRet;
}

/**
 * 数据签名P7
 * indata：明文数据
 * hashAlg: 1-SHA1, 2-SHA256, 3-SHA512, 4-MD5, 5-MD4, 6-SM3
 * return：签名数据
 */
function SignDataByP7(indata, hashAlg) {
    var result = "";
    SecCtrl.KS_SetParam("signtype", "pksc7");
    result = SecCtrl.KS_SignData(indata, hashAlg);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 数据验签P7
 * p7data：P7数据
 * hashAlg: 1-SHA1, 2-SHA256, 3-SHA512, 4-MD5, 5-MD4, 6-SM3
 * return：成功=true，失败=false
 */
function VerifyP7SignData(p7data) {
    var lRet = SecCtrl.KS_VerifyP7SignData(p7data);
    return lRet;
}


//修改初始化函数以及相应函数，避免多次初始化----------------------------

/**
 * 获取插KeyVid
 */
function GetXacaDeviceVid() {
    var result = "";

    result = SecCtrl.KS_GetDeviceVid();
    return result;
}

/**
 * 对称加密
 * indata 原文
 * key 对称密钥
 * symmAlg 对称算法
 * return: 密文
 */
function SymmEncrypt(indata, key, symmAlg) {
    var result = "";

    result = SecCtrl.KS_SymmEncrypt(indata, key, symmAlg);

    return result;
}


/**
 * 对称解密
 * encdata 密文
 * key 对称密钥
 * symmAlg 对称算法
 * return: 原文
 */
function SymmDecrypt(encdata, key, symmAlg) {
    var result = "";

    result = SecCtrl.KS_SymmDecrypt(encdata, key, symmAlg);

    return result;
}

/**
 * 组数字信封
 * indata 原文
 * cert 加密证书
 * return: 数字信封
 */
function MakeEnvelope(indata, cert) {
    var result = "";


    result = SecCtrl.KS_MakeEnvelope(indata, cert);

    return result;
}

/**
 * 解数字信封
 * envelope 数字信封
 * return: 原文
 */
function OpenEnvelope(envelope) {
    var result = "";


    result = SecCtrl.KS_OpenEnvelope(envelope);

    return result;
}

/**
 * 生成证书请求文件（双key，排除管理员key）
 * dn: 格式为C=CN,CN=测试,O=测试组织,OU=测试部门,L=上海市,S=上海市，E=admin@163.com
 * hashAlg: 签名摘要算法
 * certsn  : 排除此证书序列号的key
 */
function MakeP10(dn, hashAlg) {
    var result = "";

    result = SecCtrl.KS_MakeP10(dn, hashAlg);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}

/**
 * 保存证书
 * signCert: Base64编码签名证书
 * encCert : Base64编码加密证书
 * encKey  : Base64编码加密私钥
 */
function SaveCert(signCert, encCert, encKey) {


    var lRet = SecCtrl.KS_SaveCert(signCert, encCert, encKey);
    if (lRet != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return false;
    }
    return true;
}


/**
 * 从证书服务器得到证书
 * iType: =1, 注册表的项为indexServer。  =2，册表的项为indexSignServer
 * CertAddr, : 证书服务器地址
 * id  : id项
 */
function GetNetCert(iType, CertAddr, id) {
    var result = "";
    result = SecCtrl.KS_SetServiceAdd(iType, CertAddr);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }


    return result;
}


/**
 * 从签名服务器得到签名值
 * iType: =1, 注册表的项为indexServer。  =2，册表的项为indexSignServer
 * CertAddr, : 签名服务器地址
 * id  : id项
 */
function GetNetSign(iType, CertAddr, id, time) {
    var result = "";

    result = SecCtrl.KS_SetServiceAdd(iType, CertAddr);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }

    result = SecCtrl.KS_GetSignValueFromNet("5200000000000128686", time);
    return result;
}


function GetAllCertSN() {
    var result = "";

    result = SecCtrl.KS_GetAllCertSN();
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
    }
    return result;
}


function WriteSESToKey(json) {
    var result = "";
    result = SecCtrl.KS_SetProv(ks_provider, ks_alg, ks_path);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    result = SecCtrl.KS_GetASN1ByParseJSON(json, 2);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    result = SecCtrl.KS_SetConfigToKey(result);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    return result;
}


//type = 1;   是获得印章信息中的印章图片信息
function ReadSESInfoFromKey(type) {
    var result = "";
    result = SecCtrl.KS_SetProv(ks_provider, ks_alg, ks_path);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    var sealList = "";
    sealList = SecCtrl.KS_GetSealList();
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    var sealData = "";
    sealData = SecCtrl.KS_GetSeal(sealList);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    var picData = SecCtrl.KS_GetInfoFromSeal(sealData, type);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    return picData;
}

function DeleteSESFileFromKey() {
    var result = "";
    SecCtrl.KS_SetProv(ks_provider, ks_alg, ks_path);
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }

    result = SecCtrl.KS_DeleteFromKey();
    if (SecCtrl.KS_GetLastErrorCode() != 0) {
        alert(SecCtrl.KS_GetLastErrorMsg());
        return SecCtrl.KS_GetLastErrorCode();
    }
    return result;
}