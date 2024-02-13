import path from "path";


function processFilePaths(filePaths) {
    return filePaths.map(
        function extractFileInfo(filePath) {
            let ext = path.extname(filePath);
            return {
                extension: ext,
                baseName: path.basename(filePath, ext),
                directory: path.dirname(filePath),
            };
        }
    );
}

const filePaths = [
  "dir1/dir2/file1.txt",
  "dir1/dir3/file2.txt",
  "dir1/dir3/file3.md",
  "dir4/file4.jpg",
  "dir4/file5.pdf",
];
const info = processFilePaths(filePaths);
console.log(info);
