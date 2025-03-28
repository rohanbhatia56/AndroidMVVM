module.exports = {
  rules: {
    'header-pattern': [2, 'always', /^APPV2-\d{5} .+$/],
    'header-max-length': [2, 'always', 100],
  },
  parserPreset: {
    parserOpts: {
      headerPattern: /^APPV2-(\d{5}) (.+)$/,
      headerCorrespondence: ['ticket', 'subject']
    }
  }
};
