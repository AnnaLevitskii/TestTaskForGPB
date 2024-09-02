mkdir -p .github/workflows/reports

find build/reports/tests/ -type f -name 'index.html' | while read -r file; do
  if xmllint --xpath '//div[@id="tab0"]' "$file" >/dev/null 2>&1; then
   xmllint --xpath '//div[@id="tab0"]' "$file" > .github/workflows/reports/$(basename "$file")
  fi
done