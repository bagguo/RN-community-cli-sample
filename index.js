/**
 * @format
 * index.js是 React Native 应用在 Android 上的入口文件。而且它是不可或缺的！它可以是个很简单的文件，简单到可以只包含一行require/import导入语句
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';

AppRegistry.registerComponent(appName, () => App);
