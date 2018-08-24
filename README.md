# react-native-kiper-contacts

## Warning

`- This library is available only for android, ios library in construction!!`
`- If you want use for ios, try this library: https://github.com/rt2zz/react-native-contacts/`

## Getting started

`$ npm install react-native-kiper-contacts --save or yarn add react-native-kiper-contacts`

### Mostly automatic installation

`$ react-native link react-native-kiper-contacts`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`

- Add `import br.com.kiper.contact.KiperContactsPackage;` to the imports at the top of the file
- Add `new KiperContactsPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:
   ```
   include ':react-native-kiper-contacts'
   project(':react-native-kiper-contacts').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-kiper-contacts/android')
   ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
   ```
     implementation project(':react-native-kiper-contacts')
   ```

## Usage

tip: Always check permission for readContacts :)

```javascript
import kiperContacts from 'react-native-kiper-contacts';

const getAllExample = async () => {
  const result = await kiperContacts.getAll(0, 0);
  // result = [{ name: string, phones: [array of strings], contactId: string }]
};

const searchContactsExample = async () => {
  // types: like, like left, like right or null(null = equals)
  const filters = [{ key: 'name', value: 'test', type: 'like' }];
  const result = await kiperContacts.searchContacts(filters, 0, 0);
  // result = [{ name: string, phones: [array of strings], contactId: string }]
};
```

## Credits

www.kiper.com.br
